
package server;
import java.awt.FileDialog;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;

import CustomObjects.*;

public class LaunchServer
  implements ServerListener, ClientListener, ClientListenListener, Serializable
{
  
public static final String LOG_FILE = "ServerLog.txt";

  private int port;
  private ServerThread menu;
  private ClientThread listen;
  private LinkedList clients = new LinkedList();
  private boolean bLogging = false;
  private FileOutputStream log;
  public Control ctrl;
  public LinkedList group = new LinkedList();
  
  
  public LaunchServer()
  {
    
    
    
  
    menu = new ServerThread(this);
    // Create a listen thread
    port = menu.gs.port;
    listen = new ClientThread(this, port);
  }

  public void onSendControl(ClientListenThread cl)
  {
    //  showControl();
      cl.write(ctrl);
  }
  
  public void onServerRunning(boolean enable)
  {
    if(!enable)
    {

      for(int i = 0; i < clients.size(); i++)
      ((ClientListenThread)clients.get(i)).shutdown();
      clients.clear();
      System.out.println("Server stopped");


    }
    else
    {
        System.out.println("Server listening to port : "+port);
      writeLog("Server has started running");
    }

    listen.setServerRunState(enable);
      
  }

  public void onShutdown()
  {
    // Inform all clients that the server is shutting down
    for(int i = 0; i < clients.size(); i++)
    //  ((ClientListenThread)clients.get(i)).shutDown(); 
    menu.shutDown();
    listen.shutDown();
    writeLog("Server shut down");
    closeLogFile();
  }

  
  
  public void onListen()
  {
    menu.setServerRunState(true);
  }

  public void onClose()
  {
    menu.setServerRunState(false);
  }

  public void onNewConnection(Socket s)
  { 
   ClientListenThread cl = new ClientListenThread(this,s);
   System.out.println("Thread is creted for the request !");

  }

  public void onListenError(String description)
  {
    System.out.println("Error: " + description);
    writeLog("Error - " + description);
  }

  public void onUserLeave(ClientListenThread c)
  {
    // Remove this user
    clients.remove(c);
    
    if(!c.username.equals(""))
      for(int i = 0; i < clients.size(); i++)
        ((ClientListenThread)clients.get(i)).writeInfo();
   // writeLog("User " + c.username + " has left");  
  }

  
  
  public void onUserAuthenticate(ClientListenThread c, User user)
  {
      User x;int i;
   
      if(user.getData().equalsIgnoreCase("LogOff"))
        {
          
          System.out.println("User logging off...");
              if(!clients.contains(c))
              {
                  
                  JOptionPane.showMessageDialog(null,"Clinet does not exist and asking for log off !!");
                  return;
              }
              for(i=0;i<clients.size();i++)
              {
                  if(((ClientListenThread)clients.get(i)).username.equals(c.username))
                  {
                      System.out.println("USER LOGGING OFF IS_______>"+c.username);
                      User tm=user;
                      tm = new User(tm.getUsername(),"LogOff",tm.getTime());
                      c.write(tm);
                    
                      try
                      {
                      c.conn.close();
                      } catch (Exception e)
                      {
                          JOptionPane.showMessageDialog(null,"BHEN");
                      }
                      clients.remove(i);
                      // sendtoAll(new User(user.getUsername(),"NO",new Date()));
                      
                      System.out.println("ClientListenThread removed");
                      break;
                  }
              }
              
              c = null;
              for(i=0;i<group.size();i++)
              {
                  if(((User)group.get(i)).getUsername().equals(user.getUsername()))
                  {
                      User tm = (User) group.get(i);
                      tm = new User(tm.getUsername(),"LogOff",tm.getTime());
                      group.remove(i);
                      for(i=0;i<clients.size();i++)
                      {
                          ((ClientListenThread)clients.get(i)).write(user);
                      }
                      
                      System.out.println("User sent to all others");
                      break;
                  }
              }
              System.out.println("User successfully Logged Off...");
        
          
          
       }
           
      if(user.getData().equalsIgnoreCase("UserJoined"))
      {
        for(i=0;i<clients.size();i++)
         {
          ClientListenThread clt = (ClientListenThread) clients.get(i);
          if(clt.iam.getUsername().equals(user.getUsername()))
          {
               user.setdata("UserClashes");
               c.write(user);              
               return;
          }
         }
         System.out.println("Creating new User");
           c.userJoined(user);
            clients.add(c);   
            group.add(new User(user.getUsername(),"ActiveUser",user.getTime()));
           int j=0; 
           for(i=0;i<clients.size();i++)
           {
                ClientListenThread ct = (ClientListenThread) clients.get(i);
                System.out.println(ct.iam.getUsername()+" will receive ::");
                for(j=0;j<group.size();j++)
                {
                    System.out.println((User)group.get(j));
                    ct.write((User)group.get(j));
                }
                System.out.println("------------------------");
                
                
           }
             
      
      }
           
      
         
  }
  public void sendtoAll(Object o)
  {
      for(int i=0;i<clients.size();i++)
      {
          ((ClientListenThread)clients.get(i)).write(o);
      }
  }
  
  
  public void onUserSendMessage(ClientListenThread cl,Message msg)
  {
    // Loop through each client until we have the correct user
      if(msg.getData().equals("ToAll"))
      {
          for(int i=0;i<clients.size();i++)
          {
              ((ClientListenThread)clients.get(i)).write(msg);
          }
          return;
      }
      else
      {
          for(int i=0;i<clients.size();i++)
          {
              ClientListenThread clt = (ClientListenThread)clients.get(i);
              if(clt.username.equals(msg.getData()))
              {
                  clt.write(msg);
                  break;
              }
              
          }
      }
  }

  
   public void showControl()
      {
       System.out.println("-------------------------------------------------------");
        for(int i=0;i<ctrl.al.size();i++)
        {
            System.out.println(((User)ctrl.al.get(i)));
        }
       System.out.println("-------------------------------------------------------");
      }
  public void onUserSendFile(ClientListenThread c,FileObject flo)
  {
      for(int i=0;i<clients.size();i++)
      {
          System.out.println(((ClientListenThread)clients.get(i)).username);
          
          if(((ClientListenThread)clients.get(i)).username.equalsIgnoreCase(flo.getData()))
          {
                   ((ClientListenThread)clients.get(i)).write(flo);
          }
      }
   
     
      //System.out.println(flo.getFile().getName());
      /*
      try
      {
      
      } catch (Exception e)
      {
          System.out.print("BufferedReader can not be read "+e);
      }*/
  }
  
  
  
  public void onUserSendMessageToAll(ClientListenThread c, String message)
  {
    // Loop through each client and send the message
    for(int i = 0; i < clients.size(); i++)
    //  ((ClientListenThread)clients.get(i)).sendMessageToAll(c.username, message);
    writeLog(c.username + " sent to all\r\n" + message);
  }

  public void onLogging(boolean enable)
  {
    bLogging = enable;
    if(bLogging)
    {
      // Open the log file and append contents
      try
      {
        log = new FileOutputStream(LOG_FILE, true);
      }
      catch (IOException e) {
        bLogging = false;
      }
    }
    else
    {
      closeLogFile();
    }
  }

  public void onLogFileClear()
  {
    File f;

    // Close the log file if it is open
    if(bLogging)
      closeLogFile();

    f = new File(LOG_FILE);
    f.delete();

    // Reopen the log file if necessary
    if(bLogging)
    {
      try {
        log = new FileOutputStream(LOG_FILE, true);
      }
      catch (IOException e)
      {
        bLogging = false;
      }
    }     
  }

  public void onViewLogFile()
  {
    try
    {
      File f = new File(LOG_FILE);
      int size = (int)f.length();
      FileInputStream file = new FileInputStream(f);
      for (int i = 0; i < size; i++)
        System.out.print((char)file.read());
      file.close();
    }
    catch (IOException e) {
      System.out.println("Could not read " + LOG_FILE);
    }

    menu.anyKeyContinue();    
  }

  public void onShowConnectedUsers()
  {
    if(clients.size() == 0)
    {
      System.out.println("There are no users connected");
    }
    else
    {
      // Loop through each client and output their details
      for(int i = 0; i < clients.size(); i++)
      {
          ClientListenThread clt = ((ClientListenThread)clients.get(i));
        System.out.println((i+1) +"\t"+clt.username);
      }
    }

   // menu.anyKeyContinue();

  }

  private void writeLog(String message)
  {
    if (bLogging)
    {
      String output;
      Date time = new Date();
      output = DateFormat.getDateInstance().format(time) + " " +
               DateFormat.getTimeInstance().format(time) +
               ": " + message + "\r\n";

      try
      {
        log.write(output.getBytes());
      } catch (IOException e) {}
    }
  }

  private void closeLogFile()
  {
    if(bLogging)
    {
      // Close the log file
      try
      {
        log.close();
      }
      catch (IOException e)
      {
        bLogging = true;
      }
    }
  }


  public static void main (String[] args)
  {
    new LaunchServer();
  }
}