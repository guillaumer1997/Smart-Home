package ca.uvic.seng330.assn3;

import java.io.IOException;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import ca.uvic.seng330.assn3.appMVC.MVCMain;
import ca.uvic.seng330.assn3.appMVC.UserController;
import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.users.User;
import ca.uvic.seng330.assn3.users.UserInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserUITest {
  
  
  @Test
  public void testingRecordingCamWhenOff() {
    Hub test = new Hub();
    Camera c = new Camera(test);
    Button testbutton1 = c.getStartRecording();
    testbutton1.fire();
    assert(c.getRecordingStatus().get().equals("Not recording"));
    
  }
  
  @Test
  public void testingTurnCameraOn() {
    Hub test = new Hub();
    Camera c = new Camera(test);
    Button testbutton = c.getChangeStatus();
    testbutton.fire();
    assert(c.getStatusProper().get().equals("ON"));
  }
  
  @Test
  public void testingRecordingWhenOn() {
    Hub test = new Hub();
    Camera c = new Camera(test);
    Button testbutton2 = c.getChangeStatus();
    Button testbutton1 = c.getStartRecording();
    testbutton2.fire();
    testbutton1.fire();
    assert(c.getRecordingStatus().get().equals("Recording"));
  }
  
  @Test
  public void testingSmartPlugTurnsOn() {
    Hub test = new Hub();
    SmartPlug s = new SmartPlug(test);
    Button testbutton = s.getChangeStatus();
    testbutton.fire();
    assert(s.getStatusProper().get().equals("ON"));
  }
  
  @Test
  public void testingSmartPlugTurnsOff() {
    Hub test = new Hub();
    SmartPlug s = new SmartPlug(test);
    Button testbutton = s.getChangeStatus();
    testbutton.fire();
    testbutton.fire();
    assert(s.getStatusProper().get().equals("OFF"));
  }
  
  
  @Test
  public void testingLightBulbTurnsOn() {
    Hub test = new Hub();
    Lightbulb s = new Lightbulb(test);
    Button testbutton = s.getToggleButton();
    testbutton.fire();
    assert(s.getStatusProper().get().equals("ON"));
  }
  
  @Test
  public void testingLightBulbTurnsOff() {
    Hub test = new Hub();
    Lightbulb s = new Lightbulb(test);
    Button testbutton = s.getToggleButton();
    testbutton.fire();
    testbutton.fire();
    assert(s.getStatusProper().get().equals("OFF"));
  }


}
