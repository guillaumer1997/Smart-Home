package ca.uvic.seng330.assn3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.users.*;
import ca.uvic.seng330.assn3.devices.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hub extends Device {

  private HashMap<UUID, Device> aDevices = new HashMap<UUID, Device>();
  private HashMap<UUID, UserInterface> aUsers = new HashMap<UUID, UserInterface>();
  private Logger logger = LoggerFactory.getLogger(Hub.class);
  ObservableList<Lightbulb> Lightbulbs;
  ObservableList<Camera> Cameras;
  ObservableList<Thermostat> Thermostats;
  ObservableList<SmartPlug> SmartPlugs;
  ObservableList<Device> Devices;
  ObservableList<UserInterface>Users;
  

  public void startup() {
    // some logic about sending init messages or somethng.
  }
  
  public Hub() {
    Lightbulbs = FXCollections.observableArrayList();
    Cameras = FXCollections.observableArrayList();
    Thermostats = FXCollections.observableArrayList();
    SmartPlugs = FXCollections.observableArrayList();
    Devices = FXCollections.observableArrayList();
    Users = FXCollections.observableArrayList();
    
  }

  public void shutdown() {}

  
  public void register(Thermostat t)throws HubRegistrationException{
    if (!Thermostats.contains(t)) {
      Thermostats.add(t);
      Devices.add(t);
      
    } else {
      throw new HubRegistrationException(t + " was already registered");
    }
    
  }
  
  public void register(Camera c)throws HubRegistrationException{
    if (!Cameras.contains(c)) {
      Cameras.add(c);
      Devices.add(c);
      
    } else {
      throw new HubRegistrationException(c + " was already registered");
    }
    
  }
  
  public void register(SmartPlug s)throws HubRegistrationException{
    if (!SmartPlugs.contains(s)) {
      SmartPlugs.add(s);
      Devices.add(s);
      
    } else {
      throw new HubRegistrationException(s + " was already registered");
    }
    
  }
  public void register(Lightbulb l)throws HubRegistrationException{
    if (!Lightbulbs.contains(l)) {
      Lightbulbs.add(l);
      Devices.add(l);
      
    } else {
      throw new HubRegistrationException(l + " was already registered");
    }
    
  }

  
  public void unregister(UserInterface User) throws HubRegistrationException {
    if (!Users.contains(User)) {
      log("Unknown User unregister");
      throw new HubRegistrationException("User does not exists!");
    } 
    Users.remove(User);
    log("User removed " + User);
  }
  
  public void register(UserInterface User) throws HubRegistrationException {
    if (!Users.contains(User)) {
      Users.add(User);
      System.out.println("new user created");
    } else {
      throw new HubRegistrationException(User + " was already registered");
    }
  }

  
  public void unregister(Lightbulb l) throws HubRegistrationException{
    if(!Lightbulbs.contains(l)) {
      log("Unknown Device unregister");
      throw new HubRegistrationException("Device does not exists!");
      
    }
    Lightbulbs.remove(l);
    Devices.remove(l);
    log("Device removed " + l);
    
  }
  
  public void unregister(Camera c) throws HubRegistrationException{
    if(!Cameras.contains(c)) {
      log("Unknown Device unregister");
      throw new HubRegistrationException("Device does not exists!");
      
    }
    Cameras.remove(c);
    Devices.remove(c);
    log("Device removed " + c);
    
  }
  
  public void unregister(SmartPlug s) throws HubRegistrationException{
    if(!SmartPlugs.contains(s)) {
      log("Unknown Device unregister");
      throw new HubRegistrationException("Device does not exists!");
      
    }
    SmartPlugs.remove(s);
    Devices.remove(s);
    log("Device removed " + s);
    
  }
  public void unregister(Thermostat t) throws HubRegistrationException{
    if(!Thermostats.contains(t)) {
      log("Unknown Device unregister");
      throw new HubRegistrationException("Device does not exists!");
      
    }
    Thermostats.remove(t);
    Devices.remove(t);
    log("Device removed " + t);
    
  }
  

  /**
   * Logging. Use SLF4J to write all message traffic to the log file.
   *
   * @param logMsg
   */
  public void log(String logMsg) {
    logger.info(logMsg);
  }

  /**
   * Alerts are events that happen at the Device level. They send the alert to the hUb, which
   * redistributes to the clients
   *
   * @param pMessage
   */
  
  public void alert(Device pDevice, String pMessage) {

    // initialize the map
    JSONObject jsonMessage = new JSONMessaging(pDevice, pMessage).invoke();

    // ordinarily, we would have logic for which clients to notify
    notifyClients(jsonMessage);
    log("ALERT msg: " + pMessage + " - from Device " + pDevice.toString());
  }

  private void notifyClients(JSONObject pMsg) {
    for (UserInterface c : aUsers.values()) {
      c.notify(pMsg);
      log("Notified: " + c.toString());
    }
  }

  public Map<UUID, Device> getDevices() {
    return new HashMap<UUID, Device>(aDevices);
  }
  
  public ObservableList<Thermostat> getThermostats(){

    return Thermostats;
  }
  
  public ObservableList<Lightbulb> getLightBulbs(){
    return Lightbulbs;
  }
  
  public ObservableList<SmartPlug> getSmartPlugs(){
    return SmartPlugs;
  }
  
  public ObservableList<Camera> getCameras(){

    return Cameras;
  }
  public ObservableList<UserInterface>getUsers(){
    return Users;
  }
}