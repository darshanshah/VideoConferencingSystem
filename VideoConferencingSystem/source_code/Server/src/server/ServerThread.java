

package server;


import java.io.*;
import javax.swing.JOptionPane;

class ServerThread extends Thread implements Serializable
{
  protected ServerListener listener;

  protected boolean shutdown;
  protected boolean running;
  protected boolean logging;
  protected boolean anykey;
  
  GUIServer gs;
  
  public ServerThread(ServerListener l)
  {
      
    listener = l;
    shutdown = false;
    running = false;
    logging = false;
    anykey= false;
    gs = new GUIServer(this);
    gs.setVisible(true);
  
  }

  public void run()
  {
   
  }

  public void anyKeyContinue()
  {
    anykey = true;
  }

  private void showPressAnyKey()
  {
    System.out.println("");
    System.out.print("Press enter to continue");
  }

 
  public void shutDown()
  {
    shutdown = true;
  }

  public void setServerRunState(boolean running)
  {
    this.running = running;
  }
}