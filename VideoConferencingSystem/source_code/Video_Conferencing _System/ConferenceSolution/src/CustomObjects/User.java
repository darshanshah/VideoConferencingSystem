/*
 * User.java
 *
 * Created on March 20, 2008, 8:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package CustomObjects;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Krunal
 */
public class User extends Master implements Serializable{
    
    public static final int id=1;
    
    public Date time;
    boolean isActive ;
   
    /** Creates a new instance of User */
    public User(String un,String dt,Date t) {
        super(id,un,dt);
        this.time = t;
        isActive = false;
       
    }
    
    public void userDisconnected()
    {
        isActive = false;
    }
    
    
    public void userConnected()
    {
        isActive = true;
    }
    
    public Date getTime()
    {
        return this.time;
    }
    
    public boolean isActive()
    {
        return this.isActive;
    }
    public void activate()
    {
        isActive = true;
    }
    
    public void deActivate()
    {
        isActive = false;
    }
    
       
    
    public String toString()
    {
        return this.Username+" is connected at "+DateFormat.getInstance().format(this.time)+"\n"+isActive+"  status is :"+this.Data;
    }
}
