/*
 * Master.java
 *
 * Created on March 20, 2008, 8:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package CustomObjects;

import java.io.Serializable;

/**
 *
 * @author Krunal
 */
public class Master implements Serializable{
    
    public int id;
    String Username;
    String Data;
    static private int copies;
    
    /** Creates a new instance of Master */
    public Master(int i,String urn,String dta) {
        copies++;
        this.id = i;
        this.Username = urn;
        this.Data = dta;
    }
    public String getUsername()
    {
        return this.Username;
    }
    public String getData()
    {
        return this.Data;
    }
    public int getCopies()
    {
        return this.copies;
    }
    public int getid()
    {
        return id;
    }
    public void setUsername(String u)
    {
        Username = u;
    }
    public void setdata(String data)
    {
        this.Data = data;
    }
    
}
