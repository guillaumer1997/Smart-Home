package ca.uvic.seng330.assn3.appMVC;

import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.users.UserInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class UserController {
  
  private Hub model;
  private UserInterface User;
  
  @FXML
  private TextArea username;

  public UserController(Hub model, UserInterface User) {
    
    this.model = model;
    this.User = User;
    
    
  }
  
  public void initialize() {
    username.setText(User.getName());
  }

}
