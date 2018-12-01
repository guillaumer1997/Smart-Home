package ca.uvic.seng330.assn3;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import ca.uvic.seng330.assn3.users.*;
import ca.uvic.seng330.assn3.devices.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Hub {

  private Logger logger = LoggerFactory.getLogger(Hub.class);
  ObservableList<Lightbulb> Lightbulbs;
  ObservableList<Camera> Cameras;
  ObservableList<Thermostat> Thermostats;
  ObservableList<SmartPlug> SmartPlugs;
  ObservableList<Device> Devices;
  ObservableList<UserInterface>Users;
  ObservableList<String> logs;
  

  public void startup() {
    String temp = "";
    try {
      BufferedReader br = new BufferedReader(new FileReader("Logs.txt"));
      while(temp!=null) {
        temp = br.readLine();
        if(temp == null) {
          break;
        }
        logs.add(temp);
        
      }
      br.close();
      
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public Hub() {
    Lightbulbs = FXCollections.observableArrayList();
    Cameras = FXCollections.observableArrayList();
    Thermostats = FXCollections.observableArrayList();
    SmartPlugs = FXCollections.observableArrayList();
    Devices = FXCollections.observableArrayList();
    Users = FXCollections.observableArrayList();
    logs = FXCollections.observableArrayList();
    this.startup();
    
  }

  public void shutdown() {
    
    for(SmartPlug s : this.getSmartPlugs()) {
      s.setStatusProper(Status.OFF);
    }
    
    for(Camera c : this.getCameras()) {
      c.setStatusProper(Status.OFF);
    }
    
    for(Lightbulb l : this.getLightBulbs()) {
      l.setStatusProper(Status.OFF);
    }
    for(Thermostat t : this.getThermostats()) {
      t.setStatusProper(Status.OFF);
    }
    logs.add("INFO - SYSTEM SHUTDOWN " );
    try {
      saveLogs();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  public void saveLogs() throws IOException {
    FileWriter writer = new FileWriter("Logs.txt", true);
    for(String s : logs) {
      writer.append(s+"\n");
     
    }
    writer.close();
    
  }

  
  public void register(Thermostat t)throws HubRegistrationException{
    if (!Thermostats.contains(t)) {
      Thermostats.add(t);
      Devices.add(t);
      logs.add("ALERT - THERMOSTAT ADDED ID: " + t.getIdentifier() + " @ " + new Date().toString());

      
    } else {
      logs.add("ALERT - THERMOSTAT " + t + " was already registered" + " @ " + new Date().toString());
      throw new HubRegistrationException(t + " was already registered");
    }
    
  }
  
  public void register(Camera c)throws HubRegistrationException{
    if (!Cameras.contains(c)) {
      Cameras.add(c);
      Devices.add(c);
      logs.add("ALERT - CAMERA ADDED ID: " + c.getIdentifier() + " @ " + new Date().toString());

      
    } else {
      logs.add("ALERT - CAMERA " + c + " was already registered" + " @ " + new Date().toString());
      throw new HubRegistrationException(c + " was already registered");
    }
    
  }
  
  public void register(SmartPlug s)throws HubRegistrationException{
    if (!SmartPlugs.contains(s)) {
      SmartPlugs.add(s);
      Devices.add(s);
      logs.add("ALERT - SMARTPLUG ADDED ID: " + s.getIdentifier() + " @ " + new Date().toString());

      
    } else {
      logs.add("ALERT - SMARTPLUG " + s + " was already registered" + " @ " + new Date().toString());
      throw new HubRegistrationException(s + " was already registered");
    }
    
  }
  public void register(Lightbulb l)throws HubRegistrationException{
    if (!Lightbulbs.contains(l)) {
      Lightbulbs.add(l);
      Devices.add(l);
      logs.add("ALERT - LIGHTBULB ADDED ID: " + l.getIdentifier() + " @ " + new Date().toString());

      
    } else {
      logs.add("ALERT - SMARTPLUG " + l.getIdentifier() + " was already registered" + " @ " + new Date().toString());
      throw new HubRegistrationException(l + " was already registered");
    }
    
  }

  
  public void unregister(UserInterface User) throws HubRegistrationException {
    if (!Users.contains(User)) {
      logger.info("Unknown User unregister");
      logs.add("ALERT - UNKNOWN USER: " + " @ " + new Date().toString());

      throw new HubRegistrationException("User does not exists!");
    } 
    Users.remove(User);
    log("User removed " + User);
    logs.add("ALERT - USER REMOVED: "  + " @ " + new Date().toString());

  }
  
  public void register(UserInterface User) throws HubRegistrationException {
    if (!Users.contains(User)) {
      Users.add(User);
      logs.add("ALERT - USER ADDED " + " @ " + new Date().toString());

     // System.out.println("new user created");
    } else {
      logs.add("ALERT - USER ALREADY REGISTERED: " + User + " @ " + new Date().toString());
      throw new HubRegistrationException(User + " was already registered");
    }
  }

  
  public void unregister(Lightbulb l) throws HubRegistrationException{
    if (!Lightbulbs.contains(l)) {
      log("Unknown Device unregister");
      logs.add("ALERT - UNKNOWN LIGHTBULB: " + l.getIdentifier() + " @ " + new Date().toString());
      throw new HubRegistrationException("Device does not exists!");
      
    }
    Lightbulbs.remove(l);
    Devices.remove(l);
    logger.info("Device removed " + l);
    logs.add("ALERT - LIGHTBULB REMOVED: " + l.getIdentifier() + " @ " + new Date().toString());

    
  }
  
  public void unregister(Camera c) throws HubRegistrationException {
    if (!Cameras.contains(c)) {
      log("Unknown Device unregister");
      logs.add("ALERT - UNKNOWN CAMERA: " + c.getIdentifier() + " @ " + new Date().toString());
      throw new HubRegistrationException("Device does not exists!");
      
    }
    Cameras.remove(c);
    Devices.remove(c);
    log("Device removed " + c);
    logs.add("ALERT - CAMERA REMOVED: " + c.getIdentifier() + " @ " + new Date().toString());

    
  }
  
  public void unregister(SmartPlug s) throws HubRegistrationException{
    if (!SmartPlugs.contains(s)) {
      log("Unknown Device unregister");
      logs.add("ALERT - UNKNOWN SMARTPLUG: " + s.getIdentifier() + " @ " + new Date().toString());

      throw new HubRegistrationException("Device does not exists!");
      
    }
    SmartPlugs.remove(s);
    Devices.remove(s);
    log("Device removed " + s);
    logs.add("ALERT - SMARTPLUG REMOVED: " + s.getIdentifier() + " @ " + new Date().toString());

    
  }
  public void unregister(Thermostat t) throws HubRegistrationException{
    if (!Thermostats.contains(t)) {
      log("Unknown Device unregister");
      logs.add("ALERT - UNKNOWN THERMOSTAT: " + t.getIdentifier() + " @ " + new Date().toString());

      throw new HubRegistrationException("Device does not exists!");
      
    }
    Thermostats.remove(t);
    Devices.remove(t);
    log("Device removed " + t);
    logs.add("ALERT - THERMOSTAT REMOVED: " + t.getIdentifier() + " @ " + new Date().toString());

    
  }
  

  /**
   * Logging. Use SLF4J to write all message traffic to the log file.
   *
   * @param logMsg
   */
  public void log(String logMsg) {
    logger.info(logMsg);
    logs.add(logMsg);
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
    for (UserInterface c : Users) {
      log("Notified: " + c.toString());
    }
  }

  public ObservableList <Device> getDevices() {
    return Devices;
  }
  
  public ObservableList<Thermostat> getThermostats() {

    return Thermostats;
  }
  
  public ObservableList<Lightbulb> getLightBulbs() {
    return Lightbulbs;
  }
  
  public ObservableList<SmartPlug> getSmartPlugs() {
    return SmartPlugs;
  }
  
  public ObservableList<Camera> getCameras() {

    return Cameras;
  }
  
  public ObservableList<String> getLogs(){
    return logs;
  }
    
  public ObservableList<UserInterface> getUsers() {
    return Users;
  }
}