package ca.uvic.seng330.assn3;

import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;
import ca.uvic.seng330.assn3.devices.*;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.devices.Thermostat;
import javafx.stage.Stage;
import ca.uvic.seng330.assn3.Hub;
import org.junit.Assert;

public class MVCTest extends ApplicationTest {
  
    
  @Override
  public void start(Stage stage) throws Exception {
    
  }
  
  
  @Test //tests if a camera can be successfully registered
  public void registerCameraTest() throws HubRegistrationException {
    Hub aMed = new Hub();
    Camera cam = new Camera(aMed);
    if (aMed.getCameras().contains(cam)) {
      assert (true);
    } else {
      assert (false);
    }
  }
  @Test //tests if a lightbulb can be succesfully registered
  public void registerLightbulbTest() throws HubRegistrationException {
    Hub aMed = new Hub();
    Lightbulb lb = new Lightbulb(aMed);
    if (aMed.getLightBulbs().contains(lb)) {
      assert (true);
    } else {
      assert (false);  
    }
  }
  
  @Test //tests if a smartplug can be successfully registered
  public void registerSmartPlugTest() throws HubRegistrationException {
    Hub aMed = new Hub();
    SmartPlug sp = new SmartPlug(aMed);
    if (aMed.getSmartPlugs().contains(sp)) {
      assert (true);
    } else {
        assert (false);  
    }
  }
  
  @Test //tests if a thermostat can be succesfully registered
  public void registerThermostatTest() throws HubRegistrationException {
    Hub aMed = new Hub();
    Thermostat therm = new Thermostat(aMed);
    if (aMed.getThermostats().contains(therm)) {
      assert (true);
    } else {
      assert (false);  
    }
  } 
  
  
  
  @Test //tests if a registered Camera is successfully removed
  public void unregisterCameraTest() throws HubRegistrationException {
    Hub aMed = new Hub();
    Camera cam = new Camera(aMed);
    aMed.unregister(cam);
    if (aMed.getCameras().contains(cam)) {
      assert (false);
    } else {
      assert (true);  
    }
  }	

  @Test //tests if a registered Lightbulb is successfully removed
  
  public void unregisterLightbulbTest() throws HubRegistrationException {
    Hub aMed = new Hub();
    Lightbulb lb = new Lightbulb(aMed);
    aMed.unregister(lb);
    if (aMed.getLightBulbs().contains(lb)) {
      assert (false);
    } else {
    assert (true);  
    }
  }
  
  @Test // checks if a registered Smartplug can be successfully removed
  public void unregisterSmartPlugTest() throws HubRegistrationException {
    Hub aMed = new Hub();
    SmartPlug sp = new SmartPlug(aMed);
    aMed.unregister(sp);
    if (aMed.getSmartPlugs().contains(sp)) {
      assert (false);
    } else {
      assert (true);  
    
    }
  }	
  //checks if a registered Thermostat can be unregistered
  public void unregisterThermostatTest() throws HubRegistrationException {
    Hub aMed = new Hub();
    Thermostat therm = new Thermostat(aMed);
    aMed.unregister(therm);
    if (aMed.getThermostats().contains(therm)) {
      assert (false);
    } else {
      assert (true);  
    }
   
  }
  
  
  
  
  

  
}




