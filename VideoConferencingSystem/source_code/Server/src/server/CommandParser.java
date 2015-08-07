/*
 * CommandParser.java
 *
 * Created on March 16, 2008, 1:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package server;

import java.io.Serializable;
import CustomObjects.*;
/**
 *
 * @author Krunal
 */

public class CommandParser implements Serializable
{
  // Constants
  public static final int CONTROL           = 0;
  public static final int USER              = 1;
  public static final int MESSAGE           = 2;
  public static final int FILE               = 3;
 
  public boolean isControl,isUserjoin,isUserLeave,isServerShutdown,isReconnecting,isUser,isMessage,isFile;
 // private int iCommand;
  public Control c;
  public User u;
  public Message msg;
  public FileObject file;
 
  public void clientProtocol(boolean isClient)
  {
    
  }

  public void parseData(Master m)
  {
    isControl=isServerShutdown=isUserLeave=isUserjoin=false;
    isMessage=isFile=isUser = isReconnecting = false;
    switch(m.getid())
    {
       
        case 1:
            u = (User) m;
            System.out.println(u.getUsername());
            isUser = true;
            break;
        case 2:
            msg = (Message) m;
            isMessage = true;
            break;
        case 3:
            file = (FileObject) m;
         //   isFile = true;
            break;
            
       
    }
  }

  
}