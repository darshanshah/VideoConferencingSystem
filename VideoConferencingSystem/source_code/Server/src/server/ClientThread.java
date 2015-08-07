/*
 * ClientThread.java
 *
 * Created on March 16, 2008, 1:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package server;

/**
 *
 * @author Krunal
 */
 import java.net.*;
import java.io.*;

public class ClientThread extends Thread implements Serializable{
    
  protected ClientListener listener;

  protected boolean shutdown;
  protected boolean running;
  protected ServerSocket listen_socket;
  protected int port;

  public ClientThread(ClientListener l, int port)
  {
    listener = l;
    shutdown = false;
    running = false;
    this.port = port;

    this.start();
  }

  public void run()
  {
    Socket new_client = null;

    // Keep listening until the user exits
    while(!shutdown)
    {
      if(running)
      {
        // See if there are any new connections
        try
        {
          new_client = listen_socket.accept();
        }
        catch (IOException e)
        {
          new_client = null;
        }
        if(new_client != null)
        {
          // Create the new connection event
            System.out.println("Creating Client Object...");
          listener.onNewConnection(new_client);
            
            
        }
      }
    }
  }

  public void shutDown()
  {
    shutdown = true;
    // Disable the server listen state
    setServerRunState(false);
  }

  public void setServerRunState(boolean running)
  {
    // Only do something if the state has changed
    if(this.running != running)
    {
      if(running)
      {
        // Create the listen socket
        try
        {
          listen_socket = new ServerSocket(port);
        }
        catch(IOException e)
        {
          // There was an error to raise an event
          listener.onListenError(e.getMessage()); 
        }
          listener.onListen();
      }
      else
      {
        // Close the listen socket
        try
        {
          listen_socket.close();
          listen_socket = null;
          listener.onClose();
        }
        catch (IOException e)
        {
          // Raise the error
          listener.onListenError(e.getMessage());
        }
      }
      this.running = running;
    }
  }
}

