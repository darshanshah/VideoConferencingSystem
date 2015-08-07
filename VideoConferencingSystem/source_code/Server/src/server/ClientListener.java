/*
 * ClientListener.java
 *
 * Created on March 16, 2008, 1:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package server;
import java.net.Socket;
/**
 *
 * @author Krunal
 */
public interface ClientListener {
    public void onListen();
  public void onClose();
  public void onNewConnection(Socket s);
  public void onListenError(String description); 
}
