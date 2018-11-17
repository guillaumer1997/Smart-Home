package ca.uvic.seng330.assn3.appMVC;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.users.*;

public class LoginController {
  
   private String userName;
   private String passWord;
   
   private Hub model;
  
  @FXML private TextField username;
  @FXML private PasswordField password;
  
  
  @FXML
  private void Login(MouseEvent event) throws IOException {
    passWord = (password.getText());
    userName = (username.getText());
    for (UserInterface u : model.getUsers()) {
      if (u.getName().equals(userName) && u.getPass().equals(passWord)) {
        if (u.getStatus().equals(UserStatus.ADMIN)) {
          Parent UserPage = null;
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminUI.fxml"));
          loader.setController(new AdminController(model));
          UserPage = loader.load();  
          Scene scene = new Scene(UserPage);
          Stage stageTheEventSourceNodeBelongs = (Stage) 
              ((Node)event.getSource()).getScene().getWindow();
          stageTheEventSourceNodeBelongs.setScene(scene);
          stageTheEventSourceNodeBelongs.show();
          
          
        } else {
          Parent UserPage = null;          
          FXMLLoader loader = new FXMLLoader(getClass().getResource("UserUI.fxml"));
          loader.setController(new UserController(model,u));
          UserPage = loader.load();
          Scene scene = new Scene(UserPage);
          Stage stageTheEventSourceNodeBelongs = (Stage)
              ((Node)event.getSource()).getScene().getWindow();
          stageTheEventSourceNodeBelongs.setScene(scene);
          stageTheEventSourceNodeBelongs.show();
          
        }
      }
    }
    
    
  }
  
  public LoginController(Hub model) {
    this.model = model;
  }
  
  public Hub getModel() {
    return model;
  }
  
  @FXML
  private void createNewAccount(MouseEvent event) throws IOException {
    
    passWord = (password.getText());
    userName = (username.getText());
    UserInterface newUser = new User(model, passWord, userName);
    Parent UserPage = null;          
    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserUI.fxml"));
    loader.setController(new UserController(model,newUser));
    UserPage = loader.load();
    Scene scene = new Scene(UserPage);
    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stageTheEventSourceNodeBelongs.setScene(scene);
    stageTheEventSourceNodeBelongs.show();
    
  }
  
  @FXML
  private void createNewAdmin(MouseEvent event) throws IOException {
    passWord = (password.getText());
    userName = (username.getText());
    UserInterface newAdmin = new Admin(model, passWord, userName);
    Parent UserPage = null;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminUI.fxml"));
    loader.setController(new AdminController(model));
    UserPage = loader.load();  
    Scene scene = new Scene(UserPage);
    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stageTheEventSourceNodeBelongs.setScene(scene);
    stageTheEventSourceNodeBelongs.show();
    
    
  }
  


}
