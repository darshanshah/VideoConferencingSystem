
package conferencesolution.Client;

import java.io.Serializable;
import CustomObjects.*;
import javax.swing.JOptionPane;


public class CommandParser implements Serializable
{
  // Constants
  public static final int CONTROL           = 0;
  public static final int USER              = 1;
  public static final int MESSAGE           = 2;
  public static final int FILE               = 3;
  public static final int AUDIO               = 6;
  
 
  
  
  
  public boolean isControl,isUserjoin,isUserLeave,isServerShutdown,isReconnecting,isUser,isMessage,isFile;
 // private int iCommand;
  public Control c;
  public User u;
  public Message msg;
  public FileObject file;
 
  public CommandParser()
  {
    
      
  }
  public void clientProtocol(boolean isClient)
  {
    
  }

  public void parseData(Master m)
  {
    isControl=isServerShutdown=isUserLeave=isUserjoin=false;
    isMessage=isFile=isUser = isReconnecting = false;
    switch(m.getid())
    {
        case 0:
            JOptionPane.showMessageDialog(null,"Control received from server !");
            c = (Control) m;
            System.out.println(c);
            break;
        case 1:
            u = (User) m;
            isUser = true;
            break;
        case 2:
            msg = (Message) m;
         //   System.out.println("message detached !!!!!!!!");
            isMessage = true;
            break;
        case 3:
            file = (FileObject) m;
            isFile = true;
            break;
       
    }
  }
public Control getControl()
{
    return this.c;
}
  
}