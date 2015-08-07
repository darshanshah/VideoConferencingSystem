

package conferencesolution.Client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.format.H261Format;
import javax.swing.JOptionPane;

public class VoiceReceiver implements Runnable{
    


    
    Player player;
    String ip;
   String s;
   Thread th;
    public VoiceReceiver(String x)
    {
       s = x;
       th = new Thread(this);
       th.start();
    }
    
   public void run()
   {
       startListening();
   }
    
    
    public void stopListening()
    {
      player.stop();
      player.close();
      th = null;
    }
    
    
    public void startListening() {
    MediaLocator mrl= new MediaLocator(s);

    if (mrl == null) {
        System.err.println("Can't build MRL for RTP");
        return;
        }


     player = null;
    try {
        player = Manager.createRealizedPlayer(mrl);
        } catch (NoPlayerException e) {
    System.err.println("Error:" + e);
    return;
    } catch (MalformedURLException e) {
    System.err.println("Error:" + e);
    return;
    } catch (Exception e) {
    System.err.println("Error:" + e);
    return;
    }

    if (player != null) {
    System.out.println("Player created.");
    
    player.realize();
    // wait for realizing
    while (player.getState() != Player.Realized){
    try {
    Thread.sleep(10);
    } catch (InterruptedException e) {
    e.printStackTrace();
    }
    }
    
    player.start();
    JOptionPane.showMessageDialog(null,"Player created and started...");
    }
    else {
        
        System.err.println("Player doesn't created.");
        return;
}
}

    
    
public static void main(String[] args) {
    String ip;
    ip = "127.0.0.1";
    
    
    String url= "rtp://"+ip+":1000/audio/0";

    MediaLocator mrl= new MediaLocator(url);

    if (mrl == null) {
        System.err.println("Can't build MRL for RTP");
        System.exit(-1);
        }

// Create a player for this rtp session
    Player player = null;
    try {
        player = Manager.createRealizedPlayer(mrl);
        } catch (NoPlayerException e) {
System.err.println("Error:" + e);
System.exit(-1);
} catch (MalformedURLException e) {
System.err.println("Error:" + e);
System.exit(-1);
} catch (Exception e) {
System.err.println("Error:" + e);
System.exit(-1);
}

    if (player != null) {
    System.out.println("Player created.");
    player.realize();
    // wait for realizing
    while (player.getState() != Player.Realized){
    try {
    Thread.sleep(10);
    } catch (InterruptedException e) {
    e.printStackTrace();
    }
    }
    
    player.start();
    player.prefetch();
    
    }
    else {
        
        System.err.println("Player doesn't created.");
        System.exit(-1);
}
}

}
