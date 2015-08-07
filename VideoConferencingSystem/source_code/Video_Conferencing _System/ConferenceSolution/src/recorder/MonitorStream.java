/*
 * MonitorStream.java
 *
 * Created on May 31, 2008, 4:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package recorder;


import javax.media.*;
import javax.media.protocol.*;
import javax.media.control.*;
import javax.media.format.*;
import javax.media.util.BufferToImage;
import java.io.IOException;
import java.net.*;
import java.awt.*;

public class MonitorStream
implements PushBufferStream, MonitorControl, BufferTransferHandler {

    PushBufferStream actual = null;
    boolean dataAvailable = false;
    boolean terminate = false;
    boolean enabled = false;
    Object bufferLock = new Object();
    Buffer cbuffer = new Buffer();
    BufferTransferHandler transferHandler = null;
    Component component = null;
    MonitorCDS cds;
    BufferToImage bti = null;
    
    MonitorStream(PushBufferStream actual, MonitorCDS cds) {
	this.actual = actual;
	actual.setTransferHandler(this);
	this.cds = cds;
    }

    public javax.media.Format getFormat() {
	return actual.getFormat();
    }
	
    public void read(Buffer buffer) throws IOException {
	// Wait for data to be available
	// Doesn't get used much because the transferData
	// call is made when data IS available. And most
	// Processors/Players read the data in the same
	// thread that called transferData, although that's
	// not a safe assumption to make
	if (!dataAvailable) {
	    synchronized (bufferLock) {
		while (!dataAvailable && !terminate) {
		    try {
			bufferLock.wait(1000);
		    } catch (InterruptedException ie) {
		    }
		}
	    }
	}

	if (dataAvailable) {
	    synchronized (bufferLock) {
		// Copy the buffer attributes, but swap the data
		// attributes so that no extra copy is made.
		buffer.copy(cbuffer, true);
		dataAvailable = false;
	    }
	}
	return;
    }

    public void transferData(PushBufferStream pbs) {
	// Get the data from the original source stream
	synchronized (bufferLock) {
	    try {
		pbs.read(cbuffer);
	    } catch (IOException ioe) {
		return;
	    }
	    dataAvailable = true;
	    bufferLock.notifyAll();
	}

	// Display data if monitor is active
	if (isEnabled()) {
	    if (bti == null) {
		VideoFormat vf = (VideoFormat) cbuffer.getFormat();
		bti = new BufferToImage(vf);
	    }
	    if (bti != null && component != null) {
		Image im = bti.createImage(cbuffer);
		Graphics g = component.getGraphics();
		Dimension size = component.getSize();
		if (g != null)
		    g.drawImage(im, 0, 0, component);
	    }
	}

	// Maybe synchronize this with setTransferHandler() ?
	if (transferHandler != null && cds.delStarted)
	    transferHandler.transferData(this);
    }

    public void setTransferHandler(BufferTransferHandler transferHandler) {
	this.transferHandler = transferHandler;
    }

    public boolean setEnabled(boolean value) {
	enabled = value;
	if (value == false) {
	    if (!cds.delStarted) {
		try {
		    cds.stopDelegate();
		} catch (IOException ioe) {
		}
	    }
	} else {
	    // Start the capture datasource if the monitor is enabled
	    try {
		cds.startDelegate();
	    } catch (IOException ioe) {
	    }
	}
	return enabled;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public Component getControlComponent() {
	if (component == null) {
	    Dimension size = ((VideoFormat)getFormat()).getSize();
	    component = new MyCanvas(size);
	    component.setSize(size);
	}
	return component;
    }

    public float setPreviewFrameRate(float rate) {
	System.err.println("TODO");
	return rate;
    }
	
    public ContentDescriptor getContentDescriptor() {
	return actual.getContentDescriptor();
    }

    public long getContentLength() {
	return actual.getContentLength();
    }

    public boolean endOfStream() {
	return actual.endOfStream();
    }

    public Object [] getControls() {
	return new Object[0];
    }

    public Object getControl(String str) {
	return null;
    }

    class MyCanvas extends Canvas {
	Dimension size;
	
	public MyCanvas(Dimension size) {
	    super();
	    this.size = size;
	}

	public Dimension getPreferredSize() {
	    return size;
	}
    }
}
