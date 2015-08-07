/*
 * MonitorCDS.java
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

public class MonitorCDS extends PushBufferDataSource implements CaptureDevice {

    private PushBufferDataSource delegate = null;
    private PushBufferStream [] delStreams = null;
    private MonitorStream monitorStream = null;
    private PushBufferStream [] monitorStreams = null;
    boolean delStarted = false; // variable used by MonitorStream also
    private Control [] controls;

    public MonitorCDS(DataSource ds) {
	// Get the stream from the actual datasource
	// and create a MonitorStream from it
	// Export the MonitorControl interface of the MonitorStream
	if (ds instanceof PushBufferDataSource) {
	    delegate = (PushBufferDataSource) ds;
	    delStreams = delegate.getStreams();
	    monitorStream = new MonitorStream(delStreams[0], this);
	    monitorStreams = new PushBufferStream[] {monitorStream};
	    controls = new Control[] {monitorStream};
	}
    }

    public Object [] getControls() {
	return controls;
    }

    public Object getControl(String value) {
	if (value.equals("jmfsample.MonitorStream") ||
	    value.equals("javax.media.control.MonitorControl"))
	    return monitorStream;
	else
	    return null;
    }

    public javax.media.CaptureDeviceInfo getCaptureDeviceInfo() {
	return ((CaptureDevice)delegate).getCaptureDeviceInfo();
    }

    public FormatControl[] getFormatControls() {
	return ((CaptureDevice)delegate).getFormatControls();
    }

    public String getContentType() {
	return delegate.getContentType();
    }

    public void connect() throws IOException {
	if (delegate == null)
	    throw new IOException("Incompatible DataSource");
	// Delegate is already connected
    }

    public void disconnect() {
	monitorStream.setEnabled(false);
	delegate.disconnect();
    }

    public synchronized void start() throws IOException {
	startDelegate();
	delStarted = true;
    }

    public synchronized void stop() throws IOException {
	if (!monitorStream.isEnabled()) {
	    stopDelegate();
	}
	delStarted = false;
    }

    public Time getDuration() {
	return delegate.getDuration();
    }

    public PushBufferStream [] getStreams() {
	return monitorStreams;
    }

    void startDelegate() throws IOException {
	delegate.start();
    }

    void stopDelegate() throws IOException {
	delegate.stop();
    }

}
