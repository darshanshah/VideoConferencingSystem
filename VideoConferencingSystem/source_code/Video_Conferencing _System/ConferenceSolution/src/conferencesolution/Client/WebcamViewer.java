

package conferencesolution.Client;
import java.applet.Applet;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;

import java.util.TimerTask;
import java.util.Vector;
import javax.media.*;
import javax.media.format.*;
import javax.media.control.*;
import javax.media.util.*;
import javax.swing.*;
import java.awt.event.*;
public class WebcamViewer extends JFrame implements ControllerListener,Serializable
{
	boolean stateTransitionOK = true;
	Object waitSync = new Object();
	Processor p,audio;
        LinkedList ls;
        java.util.Timer timer;
        MediaLocator mlc;
        
	public WebcamViewer()
	{
		try
		{
 
 			p = Manager.createProcessor(new MediaLocator("vfw://0"));
                        Vector vc = javax.media.cdm.CaptureDeviceManager.getDeviceList();
                        for(int i=0;i<vc.size();i++)
                        {
                            System.out.println(((CaptureDeviceInfo)vc.get(i)).getName());
                        }
			p.addControllerListener(this);
			p.configure();
			waitForState(p.Configured);
			p.setContentDescriptor(null);
			TrackControl tc[] = p.getTrackControls();
			TrackControl videoTrack = tc[0];
                        MenuBar mb = new MenuBar();
                        Menu m = new Menu("Option");
                        MenuItem mi1 = new MenuItem("Restore Default");
                        mi1.addActionListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent ae)
                            {
                                pack();
                            }
                        });
                        mb.add(m);
                       
                        m.add(mi1);
                        this.setMenuBar(mb);
                        
                        
                        for(int i=0;i<tc.length;i++)
                        {
                            System.out.println(((TrackControl)tc[i]).getFormat().getEncoding());
                            
                        }
                       
			p.prefetch();
			waitForState(p.Prefetched);
                       ls = new LinkedList();
                      
                        ActionListener khp = new ActionListener()
                        {   int i=0;
                            public void actionPerformed(ActionEvent ae)
                            {
                           
                            }
                        };
                        Timer ti = new Timer(1000,khp);
                        ti.start();
                        
                        
       			getContentPane().add(p.getVisualComponent());
                        
 			p.start();
                        ls.add(p.getVisualComponent());
 			pack();
 			setVisible(true);
                        this.setAlwaysOnTop(true);
                        this.addWindowListener(new WindowAdapter()
                        {
                            public void windowClosing(WindowEvent we)
                            {
                               
                                p.stop();
                                 p.close();
                               dispose();
                            }
                        });
                        
		}
		catch(Exception e){System.out.println(e);}
 
	}
 
        public void showSize()
        {
            System.out.println("Ls :   "+ls.size());
                    
        }
	public static void main(String[] args)
	{
		WebcamViewer t1 = new WebcamViewer();
	}
 
    public void controllerUpdate(ControllerEvent evt)
    {
        if (evt instanceof ConfigureCompleteEvent || evt instanceof RealizeCompleteEvent || evt instanceof PrefetchCompleteEvent)
        {
            synchronized (waitSync)
            {
                System.out.println("controller called ");
                stateTransitionOK = true;
                waitSync.notifyAll();
            }
        }
        else if (evt instanceof ResourceUnavailableEvent)
        {
            synchronized (waitSync)
            {
                System.out.println("ResourceUnavailable");
                stateTransitionOK = false;
                waitSync.notifyAll();
            }
        }
        else if (evt instanceof EndOfMediaEvent)
        {
            p.close();
            System.exit(0);
    	}
	}
 
    boolean waitForState(int state)
    {
        synchronized (waitSync)
        {
            try
            {
                while (p.getState() != state && stateTransitionOK)
                    waitSync.wait();
            }
            catch (Exception e) {}
        }
        return stateTransitionOK;
    }
 
}

