

package conferencesolution.Client;

import CustomObjects.Message;


public interface ChatListener
{
  public void onSendMessageToAll(Message msg);
  public void onInitiatePrivateMessage(String username);
}