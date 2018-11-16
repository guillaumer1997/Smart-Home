package ca.uvic.seng330.assn3.users;

import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.devices.Device;

public interface UserInterface {

  public UUID getIdentifier();
  public void notify(JSONObject json) ;

  public String toString();
  public String getName();
  public UserStatus getStatus();
  public String getPass();
  
}
