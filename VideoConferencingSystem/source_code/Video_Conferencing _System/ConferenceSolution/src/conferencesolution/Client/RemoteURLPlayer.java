
package conferencesolution.Client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.*;
import javax.swing.JFrame;
public class RemoteURLPlayer implements javax.media.ControllerListener {
public Player player = null;
public boolean configured = false;
public boolean realized = false;
public boolean prefetched = false;
public boolean eom = false;
public boolean failed = false;
public boolean closed = false;
public JFrame win;
public Panel pan;

public RemoteURLPlayer(Player p) {
    player =p;
    win = new JFrame("Remote URL Locator");
    win.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent we)
        {
          //  player.deallocate();
            player.stop();
            player.close();
            win.dispose();
            removeListener();
        }
    });
    player.addControllerListener(this);
    }
public void removeListener()
{
    player.removeControllerListener(this);
}


public void start(MediaLocator ml )
{
    try
    {
 
                      player.realize();
                      player.start();
                        win.setVisible(true);
                        win.setLayout(new BorderLayout());
                        Component vc,cc;
         if ((vc = player.getVisualComponent()) != null) {
	    win.add("Center", vc);
	}

	if ((cc = player.getControlPanelComponent()) != null) {
    	    win.add("South", cc);
	}
                        
                      //  win.setTitle("This the default player...");
                        win.pack();
    /*       
    }
                 }
            }
        }
      */  
            
        
    
    } catch (Exception w)
    {
        System.out.println(w);
    }
    
    
}



public boolean configure(int timeOutMillis) {
    long startTime = System.currentTimeMillis();
    synchronized (this) {
        if (player instanceof Processor)
            ((Processor)player).configure();
        else
            return false;

        while (!configured && !failed) {
            try {
                wait(timeOutMillis);
                } catch (InterruptedException ie) {
                        }
            
            if (System.currentTimeMillis() - startTime > timeOutMillis)
                break;
            }
            }
                return configured;
            }



public boolean realize(int timeOutMillis) {
        long startTime = System.currentTimeMillis();
        synchronized (this) {
        player.realize();
            while (!realized && !failed) {
                try {
                    wait(timeOutMillis);
                    } catch (InterruptedException ie) {
                    }
                if (System.currentTimeMillis() - startTime > timeOutMillis)
                    break;
                
                    }
                    }
                    return realized;    
                }




public boolean prefetch(int timeOutMillis) {
        long startTime = System.currentTimeMillis();
        synchronized (this) {
            player.prefetch();
                while (!prefetched && !failed) {
                try {
                    wait(timeOutMillis);
                    } catch (InterruptedException ie) {
                }
                
                if (System.currentTimeMillis() - startTime > timeOutMillis)
                    break;
                }
                }   
            return prefetched && !failed;
                }



public boolean playToEndOfMedia(int timeOutMillis) {
            long startTime = System.currentTimeMillis();
                eom = false;
                synchronized (this) {
                    player.start();
                   
                   
                while (!eom && !failed) {
                try {
                    wait(timeOutMillis);
                } catch (InterruptedException ie) {
                }
            if (System.currentTimeMillis() - startTime > timeOutMillis)
                break;  
                    }   
                }
                return eom && !failed;
                }
            


public void close() {
                synchronized (this) {
                player.close();
                while (!closed) {
                try {   
                    wait(100);
                    } catch (InterruptedException ie) {
                }
                }   
                }
            player.removeControllerListener(this);
            
}
public synchronized void controllerUpdate(ControllerEvent ce) {
if (ce instanceof RealizeCompleteEvent) {
realized = true;

} else if (ce instanceof ConfigureCompleteEvent) {
configured = true;
} else if (ce instanceof PrefetchCompleteEvent) {
prefetched = true;
} else if (ce instanceof EndOfMediaEvent) {
eom = true;
} else if (ce instanceof ControllerErrorEvent) {
failed = true;
} else if (ce instanceof ControllerClosedEvent) {
closed = true;
} else {
return;
}
notifyAll();
}
}