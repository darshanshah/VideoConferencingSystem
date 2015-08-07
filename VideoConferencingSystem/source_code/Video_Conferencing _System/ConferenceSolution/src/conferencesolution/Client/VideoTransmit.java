
package conferencesolution.Client;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;
import javax.media.format.*;
import javax.media.control.TrackControl;
import javax.media.control.QualityControl;
import java.io.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VideoTransmit {

    
    private MediaLocator locator;
    private String ipAddress;
    private String port;

    private Processor processor = null;
    private DataSink  rtptransmitter = null;
    private DataSource dataOutput = null;
    public float fd;
   
    
    
    public VideoTransmit(MediaLocator locator,
			 String ipAddress,
			 String port) {
	
	this.locator = locator;
	this.ipAddress = ipAddress;
	this.port = port;
    }

    
    public synchronized String start() {
	String result;

	result = createProcessor();
	if (result != null)
	    return result;

	
	result = createTransmitter();
	if (result != null) {
	    processor.close();
	    processor = null;
	    return result;
	}

	
	processor.start();
	
	return null;
    }

   
    public void stop() {
	synchronized (this) {
	    if (processor != null) {
		processor.stop();
		processor.close();
		processor = null;
		rtptransmitter.close();
		rtptransmitter = null;
	    }
	}
    }

    private String createProcessor() {
	if (locator == null)
	    return "Locator is null";

	DataSource ds;
	DataSource clone;

	try {
	    ds = Manager.createDataSource(locator);
	} catch (Exception e) {
	    return "Couldn't create DataSource";
	}


	try {
	    processor = Manager.createProcessor(ds);
	} catch (NoProcessorException npe) {
	    return "Couldn't create processor";
	} catch (IOException ioe) {
	    return "IOException creating processor";
	} 


	boolean result = waitForState(processor, Processor.Configured);
	if (result == false)
	    return "Couldn't configure processor";


	TrackControl [] tracks = processor.getTrackControls();


	if (tracks == null || tracks.length < 1)
	    return "Couldn't find tracks in processor";

	boolean programmed = false;
	for (int i = 0; i < tracks.length; i++) {
	    Format format = tracks[i].getFormat();
	    if (  tracks[i].isEnabled() &&
		  format instanceof VideoFormat &&
		  !programmed) {
		
		
		Dimension size = ((VideoFormat)format).getSize();
		float frameRate = ((VideoFormat)format).getFrameRate();
		int w = (size.width % 8 == 0 ? size.width :
				(int)(size.width / 8) * 8);
		int h = (size.height % 8 == 0 ? size.height :
				(int)(size.height / 8) * 8);
		VideoFormat jpegFormat = new VideoFormat(VideoFormat.JPEG_RTP,
							 new Dimension(w, h),
							 Format.NOT_SPECIFIED,
							 Format.byteArray,
							 frameRate);
		tracks[i].setFormat(jpegFormat);
		System.err.println("Video transmitted as:");
		System.err.println("  " + jpegFormat);
		
		programmed = true;
	    } else
		tracks[i].setEnabled(false);
	}

	if (!programmed)
	    return "Couldn't find video track";

	
	ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW_RTP);
	processor.setContentDescriptor(cd);

	
	result = waitForState(processor, Controller.Realized);
	if (result == false)
	    return "Couldn't realize processor";

	
        
        fd = Float.parseFloat(JOptionPane.showInputDialog("Select value for quality between 0-1",0.5f).toString());
        if(!(fd>1) || !(fd <0))
            fd = 0.5f;
            
        System.out.println(fd);
        
	setJPEGQuality(processor, fd);

	
	dataOutput = processor.getDataOutput();
	return null;
    }

   
    private String createTransmitter() {
	
	String rtpURL = "rtp://" + ipAddress + ":" + port + "/video";
	MediaLocator outputLocator = new MediaLocator(rtpURL);

	
	try {
	    rtptransmitter = Manager.createDataSink(dataOutput, outputLocator);
	    rtptransmitter.open();
	    rtptransmitter.start();
	    dataOutput.start();
	} catch (MediaException me) {
	    return "Couldn't create RTP data sink";
	} catch (IOException ioe) {
	    return "Couldn't create RTP data sink";
	}
	
	return null;
    }


   
    void setJPEGQuality(Player p, float val) {

	Control cs[] = p.getControls();
	QualityControl qc = null;
	VideoFormat jpegFmt = new VideoFormat(VideoFormat.JPEG);

	
	for (int i = 0; i < cs.length; i++) {

	    if (cs[i] instanceof QualityControl &&
		cs[i] instanceof Owned) {
		Object owner = ((Owned)cs[i]).getOwner();

		
		if (owner instanceof Codec) {
		    Format fmts[] = ((Codec)owner).getSupportedOutputFormats(null);
		    for (int j = 0; j < fmts.length; j++) {
			if (fmts[j].matches(jpegFmt)) {
			    qc = (QualityControl)cs[i];
	    		    qc.setQuality(val);
			    System.err.println("- Setting quality to " + 
					val + " on " + qc);
			    break;
			}
		    }
		}
		if (qc != null)
		    break;
	    }
	}
    }


    
    private Integer stateLock = new Integer(0);
    private boolean failed = false;
    
    Integer getStateLock() {
	return stateLock;
    }

    void setFailed() {
	failed = true;
    }
    
    private synchronized boolean waitForState(Processor p, int state) {
	p.addControllerListener(new StateListener());
	failed = false;

	
	if (state == Processor.Configured) {
	    p.configure();
	} else if (state == Processor.Realized) {
	    p.realize();
	}
	
	
	while (p.getState() < state && !failed) {
	    synchronized (getStateLock()) {
		try {
		    getStateLock().wait();
		} catch (InterruptedException ie) {
		    return false;
		}
	    }
	}

	if (failed)
	    return false;
	else
	    return true;
    }

    

    class StateListener implements ControllerListener {

	public void controllerUpdate(ControllerEvent ce) {

	   
	    if (ce instanceof ControllerClosedEvent)
		setFailed();

	 
	    if (ce instanceof ControllerEvent) {
		synchronized (getStateLock()) {
		    getStateLock().notifyAll();
		}
	    }
	}
    }}
    
    


  
    
   

