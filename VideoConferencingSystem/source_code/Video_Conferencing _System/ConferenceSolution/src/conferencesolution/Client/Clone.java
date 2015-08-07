package conferencesolution.Client;



import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.media.*;
import javax.media.control.TrackControl;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;



public class Clone extends JFrame implements ControllerListener {

    Player p;
    DataSource dsc;
    Object waitSync = new Object();
    boolean stateTransitionOK = true;


   
    public void removeListener()
    {
        p.removeControllerListener(this);
    }
    
    
    public void callDispose()
    {
        this.dispose();
    }
    
    public boolean open(DataSource ds) {
      
        this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent we)
        {
            p.stop();
            removeListener();
            p.close();
            callDispose();
            
        }
        });
        
        
	System.err.println("create player for: " + ds.getContentType());
        
        

	try {
            this.setTitle("Audio/Video Player");
            
	    p = Manager.createPlayer(ds);
            
        } catch (Exception e) {
	    System.err.println("Failed to create a player from the given DataSource: " + e);
	    return false;
	}

	p.addControllerListener(this);

	// Realize the player.
	p.prefetch();
	if (!waitForState(p.Prefetched)) {
	    System.err.println("Failed to realize the player.");
	    return false;
	}

	

	setLayout(new BorderLayout());

	Component cc;

	Component vc;
	if ((vc = p.getVisualComponent()) != null) {
	    add("Center", vc);
	}

	if ((cc = p.getControlPanelComponent()) != null) {
	    add("South", cc);
	}

	
	p.start();

	setVisible(true);

	return true;
    }

    public void addNotify() {
	super.addNotify();
	pack();
        
    }

    
    boolean waitForState(int state) {
	synchronized (waitSync) {
	    try {
		while (p.getState() < state && stateTransitionOK)
		    waitSync.wait();
	    } catch (Exception e) {}
	}
	return stateTransitionOK;
    }


    
    public void controllerUpdate(ControllerEvent evt) {

	if (evt instanceof ConfigureCompleteEvent ||
	    evt instanceof RealizeCompleteEvent ||
	    evt instanceof PrefetchCompleteEvent) {
	    synchronized (waitSync) {
		stateTransitionOK = true;
		waitSync.notifyAll();
	    }
	} else if (evt instanceof ResourceUnavailableEvent) {
	    synchronized (waitSync) {
		stateTransitionOK = false;
		waitSync.notifyAll();
	    }
	} else if (evt instanceof EndOfMediaEvent) {
	    p.close();
	    dispose();
	} else if (evt instanceof SizeChangeEvent) {
	}
    }



    
    
    public void init(MediaLocator ml,int copies)
{
	if (ml == null) {
	    System.err.println("Cannot build media locator from: " + ml);
	    prUsage();
	     dispose();
	}

	DataSource ds = null;
try
{
	    ds = Manager.createDataSource(ml);
	} catch (Exception e) {
	    System.err.println("Cannot create DataSource from: " + ml);
	     dispose();
	}

	ds = Manager.createCloneableDataSource(ds);

	if (ds == null) {
	    System.err.println("Cannot clone the given DataSource");
	     dispose();
	}

	Clone clone = new Clone();

	if (!clone.open(ds))
	     dispose();

	for (int i = 1; i < copies; i++) {

	    clone = new Clone();
	    if (!clone.open(((SourceCloneable)ds).createClone()))
		 dispose();
	}
        
    }

    
    
    static void prUsage() {
	System.err.println("Usage: java Clone <url> <# of copies>");
    }
}

