

package conferencesolution.Client;


public interface LoginListener {
  public void onConnectRequest(String host, int port, String username);
  public void onLoginCancel();
  
}
