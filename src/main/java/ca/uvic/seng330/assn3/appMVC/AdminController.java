package ca.uvic.seng330.assn3.appMVC;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.devices.Thermostat;
import ca.uvic.seng330.assn3.users.*;
import java.util.UUID;

public class AdminController {
  @FXML
  private ListView<UserInterface> Users;
  @FXML
  private ListView<Camera> CamList;
  @FXML
  private ListView<Thermostat> ThermList;
  @FXML
  private ListView<SmartPlug> SmartList;
  @FXML
  private ListView<Lightbulb> LightList;
  @FXML
  private ListView<String> Logs;
  @FXML
  private TextField username;
  @FXML
  private TextField password;
  
  ObservableList<Camera> Cams;
  ObservableList<Thermostat> Therms;
  ObservableList<SmartPlug> Smarts;
  ObservableList<Lightbulb>Lights;

  
  
  private Hub model;
  
  public AdminController(Hub model) {
    this.model = model;

  }
 
  
  
  public void initialize() {
    
    Thread dataCollect = new Thread() {
      public void run() {
        
      Cams = model.getCameras();
      Therms = model.getThermostats();
      Smarts = model.getSmartPlugs();
      Lights = model.getLightBulbs();
      CamList.setItems(Cams);
      ThermList.setItems(Therms);
      SmartList.setItems(Smarts);
      LightList.setItems(Lights);
      Logs.setItems(model.getLogs());
      
      for(Camera c : model.getCameras()) {
        model.log(c.toString());
      }
      for(Lightbulb l : model.getLightBulbs()) {
        model.log(l.toString());
      }
      for(SmartPlug s : model.getSmartPlugs()) {
        model.log(s.toString());
      }
      for(Thermostat t : model.getThermostats()) {
        model.log(t.toString());
      }
      }
      
    };
    
    dataCollect.start();
    ObservableList<UserInterface> User = model.getUsers();
    Users.setItems(User);
    
    
    
    
  }
  
  @FXML
  public void unregCamera(MouseEvent event) throws HubRegistrationException {
    if(CamList.getSelectionModel().getSelectedItem()!=null) {
      model.unregister(CamList.getSelectionModel().getSelectedItem());
    }
  }
  
  @FXML
  public void unregThermostat(MouseEvent event) throws HubRegistrationException{
    if(ThermList.getSelectionModel().getSelectedItem()!=null) {
      model.unregister(ThermList.getSelectionModel().getSelectedItem());
    }
  }
  @FXML
  public void unregSmartPlug(MouseEvent event) throws HubRegistrationException{
    if(SmartList.getSelectionModel().getSelectedItem()!=null) {
      model.unregister(SmartList.getSelectionModel().getSelectedItem());
    }
  }
  @FXML
  public void unregLightbulb(MouseEvent event) throws HubRegistrationException{
    if(LightList.getSelectionModel().getSelectedItem()!=null) {
      model.unregister(LightList.getSelectionModel().getSelectedItem());
    }
  }
  
  @FXML
  public void RegLight(MouseEvent event) {
    Lightbulb l = new Lightbulb(model);
  }
  
  @FXML
  public void RegCam(MouseEvent event) {
    Camera c = new Camera(model);
  }
  
  @FXML
  public void RegTherm(MouseEvent event) {
    Thermostat t = new Thermostat(model);
  }
  
  @FXML
  public void RegSmart(MouseEvent event) {
    SmartPlug s = new SmartPlug(model);
  }
  
  @FXML
  public void createUser(MouseEvent event) {
    String pWord = password.getText();
    String uName = username.getText();
    
    UserInterface newUser = new User(model, uName, pWord);
    
  }
  
  @FXML
  public void backToLogin(MouseEvent event) throws IOException {
    Parent root = null;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
    loader.setController(new LoginController(model));
    root = loader.load();
    Scene scene = new Scene(root);
    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stageTheEventSourceNodeBelongs.setScene(scene);
    stageTheEventSourceNodeBelongs.show();
  }
  
  
  

}
