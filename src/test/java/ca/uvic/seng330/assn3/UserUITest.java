package ca.uvic.seng330.assn3;

import java.io.IOException;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import ca.uvic.seng330.assn3.appMVC.MVCMain;
import ca.uvic.seng330.assn3.appMVC.UserController;
import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.devices.Status;
import ca.uvic.seng330.assn3.devices.Temperature;
import ca.uvic.seng330.assn3.devices.Temperature.TemperatureOutofBoundsException;
import ca.uvic.seng330.assn3.devices.Temperature.Unit;
import ca.uvic.seng330.assn3.devices.Thermostat;
import ca.uvic.seng330.assn3.users.User;
import ca.uvic.seng330.assn3.users.UserInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserUITest {
  
  
  
  
  @Test
  public void testingRecordingCamWhenOff() {
    Hub test = new Hub();
    Camera c = new Camera(test);
    Button testbutton1 = c.getStartRecording();
    testbutton1.fire();
    assert(c.getRecordingStatus().getValue().equals("Not recording"));
    
  }
  
  @Test
  public void testingTurnCameraOn() {
    Hub test = new Hub();
    Camera c = new Camera(test);
    Button testbutton = c.getChangeStatus();
    testbutton.fire();
    assert(c.getStatusProper().getValue().equals("ON"));
  }
  
  @Test
  public void testingRecordingWhenOn() {
    Hub test = new Hub();
    Camera c = new Camera(test);
    Button testbutton2 = c.getChangeStatus();
    Button testbutton1 = c.getStartRecording();
    testbutton2.fire();
    testbutton1.fire();
    assert(c.getRecordingStatus().getValue().equals("Recording"));
  }
  
  @Test
  public void testingSmartPlugTurnsOn() {
    Hub test = new Hub();
    SmartPlug s = new SmartPlug(test);
    Button testbutton = s.getChangeStatus();
    testbutton.fire();
    assert(s.getStatusProper().getValue().equals("ON"));
  }
  
  @Test
  public void testingSmartPlugTurnsOff() {
    Hub test = new Hub();
    SmartPlug s = new SmartPlug(test);
    Button testbutton = s.getChangeStatus();
    testbutton.fire();
    testbutton.fire();
    assert(s.getStatusProper().getValue().equals("OFF"));
  }
  
  
  @Test
  public void testingLightBulbTurnsOn() {
    Hub test = new Hub();
    Lightbulb s = new Lightbulb(test);
    Button testbutton = s.getToggleButton();
    testbutton.fire();
    assert(s.getStatusProper().getValue().equals("ON"));
  }
  
  @Test
  public void testingLightBulbTurnsOff() {
    Hub test = new Hub();
    Lightbulb s = new Lightbulb(test);
    Button testbutton = s.getToggleButton();
    testbutton.fire();
    testbutton.fire();
    assert(s.getStatusProper().getValue().equals("OFF"));
  }
  
  @Test
  public void testingThermTurnsOn() {
    Hub test = new Hub();
    Thermostat t = new Thermostat(test);
    Button testButton = t .getToggleButton();
    testButton.fire();
    assert(t.getStatus().equals(Status.ON));
  }
  
  @Test
  public void testingThermTurnsOff() {
    Hub test = new Hub();
    Thermostat t = new Thermostat(test);
    Button testButton = t .getToggleButton();
    testButton.fire();
    testButton.fire();
    assert(t.getStatus().equals(Status.OFF));
  }
  
  @Test
  public void testThermChangesUnits() {
    Hub test = new Hub();
    Thermostat t = new Thermostat(test);
    Button testButton = t.getSwitchUnit();
    Button onButton = t.getToggleButton();
    onButton.fire();
    try {
      t.setTemp( new Temperature(25.00, Unit.CELSIUS));
    } catch (TemperatureOutofBoundsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    testButton.fire();
    assert(t.getTemp().getUnit() == Unit.FAHRENHEIT);
  }
  
  @Test
  public void testThermSetsTemp() {
    Hub test = new Hub();
    Thermostat t = new Thermostat(test);
    Button testButton = t.getSetButton();
    Button onButton = t.getToggleButton();
    TextField testField = t.getNewTemp();
    onButton.fire();
    testField.setText("25.00");
    testButton.fire();
    assert(t.getTemp().getTemperature() == 25.00);
        
  }
  
  @Test
  public void testClimateChange() {
    Hub test = new Hub();
    Thermostat t = new Thermostat(test);
    t.climateChange(15.0, Unit.CELSIUS);
    assert(t.getTemp().getTemperature() == 15.0);
    assert(t.getTemp().getUnit().equals(Unit.CELSIUS));
  }
  
  @Test
  public void testCameraDetects() {
    Hub test = new Hub();
    Camera c = new Camera(test);
    Lightbulb l = new Lightbulb(test);
    Button camButton = c.getChangeStatus();
    camButton.fire();
    c.userEntered();
    assert(l.getStatus() == Status.ON);
  }


}
