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

public class MVCMain extends Application {
  private Hub m;
  private Stage primaryStage;
  
  public void start(Stage primaryStage) throws IOException {
    m = new Hub();
    Camera c = new Camera(m);
    Camera c2 = new Camera(m);
    Parent root = null;
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
    loader.setController(new LoginController(m));
    root = loader.load();

    
    Scene scene = new Scene(root);
    this.primaryStage = primaryStage;  
    primaryStage.setScene(scene);
    primaryStage.show();
		
	}
  

   

	public static void main(String[] args) {
		launch(args);
	}

 
}
