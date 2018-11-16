package ca.uvic.seng330.assn3;

import org.junit.Test;
<<<<<<< HEAD

import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.devices.Thermostat;
import ca.uvic.seng330.assn3.users.Admin;
import ca.uvic.seng330.assn3.users.User;
import ca.uvic.seng330.assn3.users.UserStatus;
import junit.framework.Assert;

public class MVCTest {
  
 
  
  @Test //tests if a camera can be successfully registered
  public void registerCameraTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Camera cam = new Camera(aMed);
	  
	  
	 
	  if (aMed.Cameras.contains(cam)) {
		  assert(true);
	  }
	  else{
		  assert(false);  
	  }
	  
  }
  @Test //tests if a lightbulb can be succesfully registered
  public void registerLightbulbTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Lightbulb lb = new Lightbulb(aMed);
	  
	  
	 
	  if (aMed.Lightbulbs.contains(lb)) {
		  assert(true);
	  }
	  else{
		  assert(false);  
	  }
	  
  }
  
  @Test //tests if a smartplug can be successfully registered
  public void registerSmartPlugTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  SmartPlug sp = new SmartPlug(aMed);
	  
	  
	 
	  if (aMed.SmartPlugs.contains(sp)) {
		  assert(true);
	  }
	  else{
		  assert(false);  
	  }
	  
  }
  
  @Test //tests if a thermostat can be succesfully registered
  public void registerThermostatTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Thermostat therm = new Thermostat(aMed);
	  
	  ;
	 
	  if (aMed.Thermostats.contains(therm)) {
		  assert(true);
	  }
	  else{
		  assert(false);  
	  }
	  
  } 
  
  
  
  @Test //tests if a registered Camera is successfully removed
  public void unregisterCameraTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Camera cam = new Camera(aMed);
	  
	  
	  
	  aMed.unregister(cam);
	  
	  if (aMed.Cameras.contains(cam)) {
		  assert(false);
	  }
	  
	  else assert(true);  
	  
		    
  	}	

  @Test //tests if a registered Lightbulb is successfully removed
  
  public void unregisterLightbulbTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Lightbulb lb = new Lightbulb(aMed);
	  
	  
	  
	  aMed.unregister(lb);
	  
	  if (aMed.Lightbulbs.contains(lb)) {
		  assert(false);
	  }
	  
	  else assert(true);  
	 
		    
  	}	
  
  @Test // checks if a registered Smartplug can be successfully removed
  public void unregisterSmartPlugTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  SmartPlug sp = new SmartPlug(aMed);
	  
	  
	  
	  aMed.unregister(sp);
	  
	  if (aMed.SmartPlugs.contains(sp)) {
		  assert(false);
	  }
	  else assert(true);  
	  
		    
  	}	
  @Test //checks if a registered Thermostat can be unregistered
  public void unregisterThermostatTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Thermostat therm = new Thermostat(aMed);
	  
	  
	  
	  aMed.unregister(therm);
	  
	  if (aMed.Thermostats.contains(therm)) {
		  assert(false);
	  }
	  else assert(true);  
	  
		    
  	}	
  
  
  @Test 
  public void registerUser() {
	  Hub aMed = new Hub();
	  
	  User user = new User(aMed, "UN", "PW");
	  
	  if(aMed.Users.contains(user) && user.getStatus() == UserStatus.USER) {
		  assert(true);
	  }
	  else assert false;
	  
  }
  
  @Test
  public void registerAdmin() {
	  Hub aMed = new Hub();
	  
	  Admin admin = new Admin(aMed, "UN", "PW");
	  
	  if(aMed.Users.contains(admin) && admin.getStatus() == UserStatus.ADMIN) {
		  assert(true);
	  }
	  else assert false;
	  
  }
  
  @Test
  public void unregisterUsers() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  User user = new User(aMed, "UN1", "PW");
	  Admin admin = new Admin(aMed, "UN2", "PW");

	  aMed.unregister(user);
	  aMed.unregister(admin);
	  
	  if(aMed.Users.contains(admin) || aMed.Users.contains(user)) {
		  assert (false);
	  }
	  assert (true);
	  
  }
  
  

=======
import org.testfx.framework.junit.ApplicationTest;
import ca.uvic.seng330.assn3.devices.*;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.devices.Thermostat;
import javafx.stage.Stage;
import ca.uvic.seng330.assn3.Hub;
import org.junit.Assert;;

public class MVCTest extends ApplicationTest {
  
  @Test
  public void LoginTest() {
    assert(true);
  }
  
  
  @Override
  public void start(Stage stage) throws Exception {
    
  }
  
  
  @Test //tests if a camera can be successfully registered
  public void registerCameraTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  Camera cam = new Camera(aMed);
	  if (aMed.getCameras().contains(cam)) {
		  assert(true);
	  }
	  else{
		  assert(false);  
	  }
  }
  @Test //tests if a lightbulb can be succesfully registered
  public void registerLightbulbTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Lightbulb lb = new Lightbulb(aMed);
	  
	 
	  if (aMed.getLightBulbs().contains(lb)) {
		  assert(true);
	  }
	  else{
		  assert(false);  
	  }
	  
  }
  
  @Test //tests if a smartplug can be successfully registered
  public void registerSmartPlugTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  SmartPlug sp = new SmartPlug(aMed);
	  
	 
	  if (aMed.getSmartPlugs().contains(sp)) {
		  assert(true);
	  }
	  else{
		  assert(false);  
	  }
	  
  }
  
  @Test //tests if a thermostat can be succesfully registered
  public void registerThermostatTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Thermostat therm = new Thermostat(aMed);
	  
	 
	  if (aMed.getThermostats().contains(therm)) {
		  assert(true);
	  }
	  else{
		  assert(false);  
	  }
	  
  } 
  
  
  
  @Test //tests if a registered Camera is successfully removed
  public void unregisterCameraTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Camera cam = new Camera(aMed);
	  
	  
	  aMed.unregister(cam);
	  
	  if (aMed.getCameras().contains(cam)) {
		  assert(false);
	  }
	  
	  else assert(true);  
	  
		    
  	}	

  @Test //tests if a registered Lightbulb is successfully removed
  
  public void unregisterLightbulbTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Lightbulb lb = new Lightbulb(aMed);
	  
	  
	  aMed.unregister(lb);
	  
	  if (aMed.getLightBulbs().contains(lb)) {
		  assert(false);
	  }
	  
	  else assert(true);  
	 
		    
  	}	
  
  @Test // checks if a registered Smartplug can be successfully removed
  public void unregisterSmartPlugTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  SmartPlug sp = new SmartPlug(aMed);
	  
	  
	  aMed.unregister(sp);
	  
	  if (aMed.getSmartPlugs().contains(sp)) {
		  assert(false);
	  }
	  else assert(true);  
	  
		    
  	}	
  @Test //checks if a registered Thermostat can be unregistered
  public void unregisterThermostatTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Thermostat therm = new Thermostat(aMed);
	  
	  
	  aMed.unregister(therm);
	  
	  if (aMed.getThermostats().contains(therm)) {
		  assert(false);
	  }
	  else assert(true);  
	  
		    
  	}	
  
  
  @Test 
  public void registerUser() {
	  
	  
  }
  
  
  
  
>>>>>>> branch 'master' of https://github.com/SENG330/assn3-team-guillaume-and-scott
  
}




