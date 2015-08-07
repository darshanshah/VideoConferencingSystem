

package conferencesolution.Client;

import java.io.*;
import CustomObjects.*;

public class ListenThread extends Thread implements Serializable
{
  private BufferedReader in;
  private ListenThreadListener listener;
  private ObjectInputStream obis;
  private Master m;
 
  
  public ListenThread(ListenThreadListener listener, ObjectInputStream in)
  {
     
    this.listener = listener;
    obis = in;
try
   {
    obis = in;
   } catch (Exception e)
   {
       
   }
           
    //System.out.println("Strating service....");
    this.start();
  }

  public void run()
  {
    String data="";
    
    // Keep receiving data and posting it back
    try
    {
      for(;;)
      {
       //  data = in.readLine();
        //  System.out.println("Client Reading...");
        m = (Master)obis.readObject();
       //   System.out.println("Client reading completes");
      if(m!=null)
             listener.onDataReceived(m);
         }
      }
    catch (Exception e)
    {
        System.out.println("ListenerThread exception "+e);
        listener.onDataError(e.getMessage());
    }
  }
  
  
  
}