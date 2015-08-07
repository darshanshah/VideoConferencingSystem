

package server;
import CustomObjects.*;

public interface ClientListenListener {
     public void onUserLeave(ClientListenThread c);
  public void onUserAuthenticate(ClientListenThread c, User user);
  public void onUserSendMessage(ClientListenThread c, Message msg);
  public void onSendControl(ClientListenThread c);
  public void onUserSendFile(ClientListenThread c,FileObject flo);
  
}
