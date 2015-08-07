
package conferencesolution.Client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.DataSink;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.NoProcessorException;
import javax.media.NotRealizedError;
import javax.media.Player;
import javax.media.Processor;
import javax.media.control.FormatControl;
import javax.media.control.TrackControl;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;

public class VoiceTransmitter implements  Runnable{


   Processor processor;
    String ip;
    URL url;
    Thread th;
    String port;
    public VoiceTransmitter(String i,String p)
    {
        ip = i;
        
        port = p;
        th = new Thread(this);
        th.start();
    }

    public void run()
    {
        startSpeaking();
    }
    
    public void stopSpeaking()
    {
        processor.stop();
        processor.close();
        th = null;
    }
    
    public void reStartSpeaking()
    {
        processor.start();
    }
    
   
    public void startSpeaking()
 {

        processor = null;
        try {
           
            
            processor = Manager.createProcessor(new MediaLocator("dsound://0"));
            
            } catch (Exception e) {
                System.exit(-1);
                
                }


        processor.configure();

        while (processor.getState() != Processor.Configured){
            try {
            Thread.sleep(100);
            } catch (InterruptedException e) {

        e.printStackTrace();
    }
    }

        processor.setContentDescriptor(
        new ContentDescriptor( ContentDescriptor.RAW));

        TrackControl track[] = processor.getTrackControls();

        boolean encodingOk = false;



        for (int i = 0; i < track.length; i++) {
if (!encodingOk && track[i] instanceof FormatControl) { 
    if (((FormatControl)track[i]).
    setFormat( new AudioFormat(AudioFormat.GSM_RTP,8000,16,1)) == null) {

        track[i].setEnabled(false);
    }
else {
encodingOk = true;
}
} else {

track[i].setEnabled(false);
}
}


if (encodingOk) {
processor.realize();
while (processor.getState() != Processor.Realized){
try {
Thread.sleep(100);
} catch (InterruptedException e) {

e.printStackTrace();
}
}

DataSource ds = null;

try {
ds = processor.getDataOutput();
} catch (NotRealizedError e) {
System.exit(-1);
}


try {
   
    
        String url= "rtp://"+ip+":"+port+"/audio/0";




    MediaLocator m = new MediaLocator(url);
    
    DataSink d = Manager.createDataSink(ds, m);
    d.open();
   
    d.start();
    processor.setRate(256000.0f);
    processor.start();  
    
    } catch (Exception e) {
        System.out.println(e);
    System.exit(-1);
    } 
    } 



}

    
    
    
    
    
    
    
    
    
    
    
    
    /*
    
    public static void main(String[] args) {
// First find a capture device that will capture linear audio
// data at 8bit 8Khz
        AudioFormat format= new AudioFormat(AudioFormat.G729_RTP,8000,8,1);

Vector devices= CaptureDeviceManager.getDeviceList( format);

CaptureDeviceInfo di= null;

if (devices.size() > 0) {
di = (CaptureDeviceInfo) devices.elementAt( 0);
}
else {
// exit if we could not find the relevant capturedevice.
System.exit(-1);
}

// Create a processor for this capturedevice & exit if we
// cannot create it
Processor processor = null;
try {
processor = Manager.createProcessor(di.getLocator());
} catch (IOException e) {
System.exit(-1);
} catch (NoProcessorException e) {
System.exit(-1);
}

// configure the processor 
processor.configure();

while (processor.getState() != Processor.Configured){
try {
Thread.sleep(100);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

processor.setContentDescriptor(
new ContentDescriptor( ContentDescriptor.RAW));

TrackControl track[] = processor.getTrackControls();

boolean encodingOk = false;

// Go through the tracks and try to program one of them to
// output gsm data.

for (int i = 0; i < track.length; i++) {
if (!encodingOk && track[i] instanceof FormatControl) { 
    if (((FormatControl)track[i]).
    setFormat( new AudioFormat(AudioFormat.GSM_RTP,8000,16,1)) == null) {

        track[i].setEnabled(false);
    }
else {
encodingOk = true;
}
} else {
// we could not set this track to gsm, so disable it
track[i].setEnabled(false);
}
}

// At this point, we have determined where w7e can send out
// gsm data or not.
// realize the processor
if (encodingOk) {
processor.realize();
while (processor.getState() != Processor.Realized){
try {
Thread.sleep(100);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
// get the output datasource of the processor and exit
// if we fail
DataSource ds = null;

try {
ds = processor.getDataOutput();
} catch (NotRealizedError e) {
System.exit(-1);
}

// hand this datasource to manager for creating an RTP
// datasink our RTP datasink will multicast the audio
try {
    //String url= "rtp://127.0.0.1:1000/audio/0";
    
        String url= "rtp://127.0.0.1:1000/audio/0";

//String url= "file://F:/New Folder/Songs/New Hindi Films/PYAAR KE SIDE EFFECTS/05 JAANE KYA.MP3";


    MediaLocator m = new MediaLocator(url);

    DataSink d = Manager.createDataSink(ds, m);
   // d.setOutputLocator(m);
    d.open();
    d.start();
    
    processor.start();  
    } catch (Exception e) {
        System.out.println(e);
    System.exit(-1);
    } 
    } 



}
*/
}
