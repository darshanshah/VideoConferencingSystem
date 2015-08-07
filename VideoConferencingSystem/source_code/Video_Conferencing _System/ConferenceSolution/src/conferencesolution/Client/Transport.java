    

package conferencesolution.Client;


import conferencesolution.*;
import java.net.*;
import java.io.*;

import java.util.*;
import javax.swing.*;
import java.util.zip.*;
import conferencesolution.Client.*;
import CustomObjects.*;
public class Transport extends CommandParser implements ListenThreadListener,Serializable
{
  String username;
  private TransportListener listener;
  
  private ListenThread listen_thread = null;
  Socket conn = null;
  
  private GZIPOutputStream cout;
  private GZIPInputStream cin;
  private ObjectOutputStream obos;
  private ObjectInputStream obis;
//  private static Control ctr;
  
  Transport( TransportListener l )
  {
    clientProtocol(true);
    listener = l;
    //ctr = new Control();
  }

  public void connect(String remoteHost, int port)
  {
    // Connect to the remote client
    try
    {
     //   System.out.println("Creating Transportt.....");
      conn = new Socket(remoteHost, port);
 
      //cout =    new GZIPOutputStream(conn.getOutputStream());
      //cin =     new GZIPInputStream(conn.getInputStream());
    
    //  obis =     new ObjectInputStream(new GZIPInputStream(conn.getInputStream()));
   // obos =     new ObjectOutputStream(new GZIPOutputStream(conn.getOutputStream()));  
         
    obis =     new ObjectInputStream(conn.getInputStream());
    obos =     new ObjectOutputStream(conn.getOutputStream());  
            listen_thread = new ListenThread(this,obis);
            listener.onConnect(); 
    }
    catch(IOException e)
    {
      listener.onConnectError(e.getMessage());
    }
  }

  
  public void write(Object o)
  {
      try
      {
       //   System.out.println("Client Writting...");
          obos.writeObject(o);
       //   System.out.println("Client Wtritten");
      } catch (Exception e)
      {
          System.out.print(e);
      }
  }
  
 
  
  public void onDataReceived(Master m)
  {
    //  System.out.println("Data received at Transport "+m.getCopies());
    parseData(m);
    
    switch(m.getid())
    {
      case 0:
        
        break;    
        case 1:  
        {
            if(u.getData().equalsIgnoreCase("UserJoined"))
            {
                listener.onUserValidated();
                break;
            }
            if(u.getData().equalsIgnoreCase("ActiveUser"))
                {
                    listener.onControlReceived(u);
                    break;
                }
            if(u.getData().equals("LogOff"))
            {
                System.out.println(this.username+" has received log off req of "+u.getUsername());
                listener.onUserLeave(u);
                break;
            }
            if(u.getData().equalsIgnoreCase("UserActivated"))
            {
                    listener.onUserValidated();
                    break;
            }
            if(u.getData().equalsIgnoreCase("LogOff"))
            {
                listener.onUserLeave(u);
                break;
            }
            if(u.getData().equalsIgnoreCase("UserClashes"))
            {
                    listener.onConnectError(u.getData());
                    break;
            }
            
        }
        case 2:      
           // System.out.println("In case 2 of Transport!");
          //  System.out.println(this.msg.getmessage());
            if(msg.getData().equalsIgnoreCase("ToAll"))
                listener.onMessageReceivedFromAll(msg.getUsername(),msg.getmessage());
            else
                listener.onMessageReceived(msg.getUsername(),msg.message);
            break;
        case 3:
            listener.onReceiveFile(file);
            break;
       
    }
    
  }
    
  public void onDataError(String description)
  {
    listener.onLostConnection();
  }
}