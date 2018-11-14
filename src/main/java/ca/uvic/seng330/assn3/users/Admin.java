package ca.uvic.seng330.assn3.users;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.devices.Device;

public class Admin implements UserInterface {
  private Hub userHub;
  private final UserStatus status = UserStatus.ADMIN;
  private final String userName;
  private final String password;
  private final UUID uuid = UUID.randomUUID();
  private Logger logger = LoggerFactory.getLogger(Hub.class);
  private JSONObject aJsonObj;
  
  public Admin(Hub model, String userName, String password) {
    try {
      model.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
    this.userName = userName;
    this.password = password;
  }
  
  public void notify(JSONObject json) {
    logger.info(json.toString());
    this.aJsonObj = json;
    try {
      display();
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  
  public UUID getIdentifier() {
    return uuid;
  }
  public UserStatus getStatus() {
    return status;
  }
  
  public String getName() {
    return userName;
  }
  
  private void display() throws JSONException {
    System.out.println("WebClient is displaying content from : " + aJsonObj.getString("node_id"));
    //TODO  should be on web page
  }
  public String toString() {
  
  return status.toString()+": "+userName;
  }


}
