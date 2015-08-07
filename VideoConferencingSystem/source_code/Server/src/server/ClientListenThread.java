

package server;
import java.net.*;
import java.io.*;
import java.util.zip.*;
import javax.swing.JOptionPane;
import CustomObjects.*;

class ClientListenThread extends Thread implements Serializable
{
 
  
  
  protected ClientListenListener listener;
  protected Socket conn;
  public String username;
  protected PrintStream out;
  protected BufferedReader in;
  protected boolean running;
  CommandParser cmd;
  
  
  protected GZIPInputStream gis;
  protected GZIPOutputStream gos;
  public StringBuffer sbr;
  int tm=0;
   ObjectInputStream obis;
  ObjectOutputStream obos;
  public User iam=null;
 
  
  
  public ClientListenThread(ClientListenListener l, Socket s)
  {
    
    listener = l;
    conn   = s;
    running = true;
    username = "";
    cmd = new CommandParser();
  
  
    try
    {
        
        
        obos = new ObjectOutputStream((s.getOutputStream()));
        obis = new ObjectInputStream((s.getInputStream())); 
        
         
        
    } catch(Exception e)
    {
      try
      {
          conn.close();
        
      }
      catch (Exception e2) {
      System.err.println(e2);}
      
      return;
    }

    this.start();
    System.out.println("Thread created and receiveing data from client !");
  }

  
  
  public void run()
  {
    while(running)
    {
      try
      {
          System.out.println("Server reading...");
        Master m = (Master) obis.readObject();
       
        cmd.parseData(m);
        if(m.getid()==0)
        {
                   System.out.println("Client requesting for is Control !");
                   listener.onSendControl(this);
  
        }
        if(m.getid()==1)
        {
           // System.out.println("Authenticating user");
            listener.onUserAuthenticate(this,cmd.u);
            
        }
        
        if(m.getid()==2)
        {
            listener.onUserSendMessage(this,cmd.msg);
         
        }
        
        if(m.getid()==3)
        {
            System.out.println("Sending file..");
            listener.onUserSendFile(this,cmd.file);
        }
        
        
      }
      catch (Exception e)
      {
        
          System.out.println("Exception in ClinetListenThread  :  "+e);
        running = false;
        try
        {
          if(conn != null)
            conn.close();
        }
        catch (IOException e2) {}
        conn = null;
        // Call the user left event
        listener.onUserLeave(this);
      }
    }
  }

  
  public void stopThread()
  {
      try
      {
      obos.close();
      obis.close();
      running = false;
      } catch (Exception e)
      {
          System.err.println("UNABLE TO CLOSE STREAMS FOR "+username);
      }
  }
  public void write(Object o)
  {
    
      try
      {
      obos.writeObject(o);
     
      } catch (Exception e)
      {
          System.out.print(e);
      }
  }
  
  public void userJoined(User u)
  {
      iam = u;
      write(iam);
      iam.setdata("ActiveUser");
      this.username = iam.getUsername();
      
  }
  
  public void writeInfo()
  { 
   
      User nw = new User(iam.getUsername(),iam.getData(),iam.getTime());
      write(nw);
      
  }
  
  public void userIsactive(User u)
  {
      u.setdata("user activated");
      u.activate();
      write(u);
  }
  
  public void userInactive(User u)
  {
      u.deActivate();
      write(u);
  }
  
  public void shutdown()
  {
      System.out.println("Server Shutting down");
  }
}