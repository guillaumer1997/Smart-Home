package ca.uvic.seng330.assn3.appMVC;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import ca.uvic.seng330.assn3.*;
import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.users.Admin;
import ca.uvic.seng330.assn3.users.User;
import ca.uvic.seng330.assn3.users.UserInterface;

public class MVCMain extends Application {
  public Hub m;
  private Stage primaryStage;
  
  public void start(Stage primaryStage) throws IOException {

    m = new Hub();
    Camera c = new Camera(m);
    Camera c2 = new Camera(m);
    Camera c3 = new Camera(m);
    Lightbulb l = new Lightbulb(m);
    Lightbulb l2 = new Lightbulb(m);
    SmartPlug s = new SmartPlug(m);
    SmartPlug s2 = new SmartPlug(m);
    UserInterface u = new User(m,"gui","user");
    UserInterface a = new Admin(m,"scott","admin");
    Parent root = null;
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
    loader.setController(new LoginController(m));
    root = loader.load();

    
    Scene scene = new Scene(root);
    this.primaryStage = primaryStage;  
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  public Hub getHub() {

    return m;
  }
  

   

  public static void main(String[] args) {
    launch(args);
  }

 
}
