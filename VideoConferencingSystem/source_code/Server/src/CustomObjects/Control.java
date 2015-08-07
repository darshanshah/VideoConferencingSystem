/*
 * Control.java
 *
 * Created on March 20, 2008, 8:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package CustomObjects;
import java.io.Serializable;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Krunal
 */
public class Control extends Master implements Serializable{
    
    public static final int id=0;
    public LinkedList al;
    public ArrayList users;
    /** Creates a new instance of Control */
    public Control() {
        super(id,"","I am something...");
      
        al = new LinkedList();
       users = new ArrayList(20);
    }
    
    public boolean isExists(User u)
    {
        for(int i=0;i<al.size();i++)
        {
            if(u.getUsername().equals(((User)al.get(i)).getUsername()))
            return true;
            
        }
        return false;
    }
    
    public int addUser(User u)
    {
        if(isExists(u))
        {
            return 1;
           //     return 2;
        }
        al.add(u);
        users.add(u.getUsername());
        return 0;
    }
    public int removeUser(User u)
    {
        if(isExists(u))
        {
            System.out.println("User Successfully removed from control !");
            System.out.println("very IMP  :  "+al.remove(u));
            return 1;
        }
        return 0;
        
    }
   
    
    public int stopuser(User u)
    {
        if(isExists(u))
        {
            if(((User)al.get(al.indexOf(u))).isActive)
            {
                ((User)al.get(al.indexOf(u))).isActive = false;
                return 1;
            }
            return 2;
        }
        return 0;
    }
    
    public int startuser(User u)
    {
        if(isExists(u))
        {
            if(!((User)al.get(al.indexOf(u))).isActive)
            {
                ((User)al.get(al.indexOf(u))).isActive = true;
                return 1;
            }
            return 2;
        }
        return 0;
    }
    public void clear()
    {
        al.clear();
    }
    
    public void showInfo()
    {
        for(int i=0;i<al.size();i++)
        {
            System.out.println(((User)al.get(i)));
        }
        System.out.println("Total Objects created are : "+this.getCopies());
    }
 
    public int updateUser(User old,User nw)
    {
        if(isExists(old))
        {
            al.set(al.indexOf(old),nw);
            return 1;
        }
        return 0;
    }
    
    public String toString()
    {
        String s = this.Username+"\t"+this.Data+"\t";
        String p="";
        for(int i=0;i<al.size();i++)
        {
            p = p + ((User)al.get(i)).toString()+"\n";
        }
        return s+"\n"+p;
        
    }
    
}
