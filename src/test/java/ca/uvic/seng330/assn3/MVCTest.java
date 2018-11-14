package ca.uvic.seng330.assn3;

import org.junit.Test;

import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.devices.Thermostat;
import junit.framework.Assert;

public class MVCTest {
  
  @Test
  public void LoginTest() {
    assert(true);
  }
  
  
  @Test //tests if a camera can be successfully registered
  public void registerCameraTest() throws HubRegistrationException {
	  Hub aMed = new Hub();
	  
	  Camera cam = new Camera(aMed);
	  
	  aMed.register(cam);
	 
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
	  
	  aMed.register(lb);
	 
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
	  
	  aMed.register(sp);
	 
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
	  
	  aMed.register(therm);
	 
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
	  
	  aMed.register(cam);
	  
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
	  
	  aMed.register(lb);
	  
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
	  
	  aMed.register(sp);
	  
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
	  
	  aMed.register(therm);
	  
	  aMed.unregister(therm);
	  
	  if (aMed.Thermostats.contains(therm)) {
		  assert(false);
	  }
	  else assert(true);  
	  
		    
  	}	
  
  
  
  
  
  
}




