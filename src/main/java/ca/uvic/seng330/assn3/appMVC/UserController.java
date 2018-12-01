package ca.uvic.seng330.assn3.appMVC;

import java.io.IOException;
import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.devices.Thermostat;
import ca.uvic.seng330.assn3.users.UserInterface;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserController {
  
  private Hub model;
  private UserInterface User;
  ObservableList<Camera> Cams;
  ObservableList<Thermostat> Therms;
  ObservableList<SmartPlug> Smarts;
  ObservableList<Lightbulb>Lights;
  
  @FXML
  private TextArea username;
  @FXML
  private TableView<Camera> CameraTable;
  @FXML
  private TableColumn<Camera, String> CamList;
  @FXML
  private TableColumn<Camera, Button> StreamList;
  @FXML
  private TableColumn<Camera,Button> StartRecording;
  @FXML
  private TableColumn<Camera,String> RecordingStatus;
  @FXML
  private TableColumn<Camera, Button> ChangeCameraStatus;
  @FXML
  private TableColumn<Camera,String> CameraStatus;
  @FXML
  private TableView<Thermostat> ThermostatTable;
  @FXML
  private TableColumn<Thermostat,String> ThermStatus;
  @FXML
  private TableColumn<Thermostat, Button> ThermToggle;
  @FXML
  private TableColumn<Thermostat, String> ThermTemp;
  @FXML
  private TableColumn<Thermostat, Button> ThermUnit;
  @FXML
  private TableColumn<Thermostat, String> ThermList;
  @FXML
  private TableView<SmartPlug> SmartPlugTable;
  @FXML
  private TableColumn<SmartPlug,String> SmartPlugList;
  @FXML
  private TableColumn<SmartPlug, String> SmartPlugStatus;
  @FXML
  private TableColumn<SmartPlug, Button> SmartPlugToggle;
  @FXML
  private TableView<Lightbulb>LightTable;
  @FXML
  private TableColumn<Lightbulb,String>LightStatus;
  @FXML
  private TableColumn<Lightbulb, Button>LightToggle;
  @FXML
  private TableColumn<Lightbulb,String>LightList;
  @FXML
  private TableColumn<Thermostat, Button> setButton;
  @FXML
  private TableColumn<Thermostat, TextField> newTemp;
  
  
 

  public UserController(Hub model, UserInterface User) {
    this.model = model;
    this.User = User;
    Cams = model.getCameras();
    Therms = model.getThermostats();
    Smarts = model.getSmartPlugs();
    Lights = model.getLightBulbs();
    
    
  }
  
  public void initialize() {
    username.setText(User.getName());
    
    //code to fill out Camera Table
    CamList.setCellValueFactory(cellData -> cellData.getValue().getNameProper());
    StartRecording.setCellValueFactory(new PropertyValueFactory<>("startRecording"));
    RecordingStatus.setCellValueFactory(cellData -> cellData.getValue().getRecordingStatus());
    CameraStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProper());
    StreamList.setCellValueFactory(new PropertyValueFactory<>("openStream"));
    ChangeCameraStatus.setCellValueFactory(new PropertyValueFactory<>("changeStatus"));
    CameraTable.setItems(model.getCameras());
    
    //code to fill out Thermostat Table
    ThermList.setCellValueFactory(cellData -> cellData.getValue().getNameProper());
    ThermStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProper());
    ThermTemp.setCellValueFactory(cellData -> cellData.getValue().getTempProper());
    ThermUnit.setCellValueFactory(new PropertyValueFactory<>("switchUnit"));
    ThermToggle.setCellValueFactory(new PropertyValueFactory<>("toggleButton"));
    setButton.setCellValueFactory(new PropertyValueFactory<>("setButton"));
    newTemp.setCellValueFactory(new PropertyValueFactory<>("newTemp"));
    ThermostatTable.setItems(model.getThermostats());
    
    //code to fill out SmartPlug table
    SmartPlugList.setCellValueFactory(cellData -> cellData.getValue().getNameProper());
    SmartPlugStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProper());
    SmartPlugToggle.setCellValueFactory(new PropertyValueFactory<>("changeStatus"));
    SmartPlugTable.setItems(model.getSmartPlugs());
    
    //code to fill out Lightbulb table
    LightList.setCellValueFactory(cellData -> cellData.getValue().getNameProper());
    LightStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProper());
    LightToggle.setCellValueFactory(new PropertyValueFactory<>("toggleButton"));
    LightTable.setItems(model.getLightBulbs());

    
  }
  
  
  
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
  
  @FXML
  public void shutdownSystem(MouseEvent e) throws IOException {
    model.shutdown();
  }

}
