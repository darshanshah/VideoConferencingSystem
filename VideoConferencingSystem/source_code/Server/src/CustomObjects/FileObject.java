/*
 * FileObject.java
 *
 * Created on March 20, 2008, 8:34 PM
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
public class FileObject extends Master implements Serializable{
    
    public static final int id = 3;
    java.io.File file;
   Object obj;
    
    public FileObject(String username,String data,File f,Object o) {
        super(id,username,data);
        this.file = f;
        obj = o;
     }
    
    public File getFile()
    {
        return this.file;
    }
    
    public Object getFileObject()
    {
        return this.obj;
    }
    
    
}
