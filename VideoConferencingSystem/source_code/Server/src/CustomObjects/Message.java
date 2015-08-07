/*
 * Message.java
 *
 * Created on March 20, 2008, 8:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package CustomObjects;
import java.io.*;
/**
 *
 * @author Krunal
 */
public class Message extends Master implements Serializable{
    
    public static final int id=2;
    public String message;
    
    public Message(String username,String data,String message) {
        super(id,username,data);
        this.message = message;
    }
    
    public String getmessage()
    {
        return this.message;
    }
    
    public final int getId()
    {
        return id;
    }
    
    
}
