package ca.uvic.seng330.assn3;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import ca.uvic.seng330.assn3.appMVC.LoginController;
import ca.uvic.seng330.assn3.appMVC.MVCMain;
import ca.uvic.seng330.assn3.users.User;
import ca.uvic.seng330.assn3.users.UserInterface;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ca.uvic.seng330.assn3.Hub;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import java.io.IOException;


public class LoginUITest extends ApplicationTest  {
  
  private Hub m;
  LoginController c;
  
  @Override
  public void start(Stage stage) throws IOException   {
   Parent root = null;
   m = new Hub();
   c = new LoginController(m);
   FXMLLoader loader = new FXMLLoader(MVCMain.class.getResource("LoginUI.fxml"));
   loader.setController(c);
   root = loader.load();
   Scene scene = new Scene(root); 
   stage.setScene(scene);
   stage.show();
    
  }

  
  
  
  @Test
  public void TestLoginButtonsExist() {
    verifyThat("#LoginButton", hasText("login"));

  }
  
  @Test
  public void TestAdminButtonExists() {
    verifyThat("#newAc", hasText("Create new account"));
  }
  
  @Test
  public void TestUserExists() {
    verifyThat("#AdminBut", hasText("Create new Admin"));
  }
  
  
  /*
  @Test
  public void TestUserGetsCreated() {
    
    clickOn("#AdminBut");
    if(m.getUsers().isEmpty()) {
      assert(false);
    }
    
  }
  
  */
  
  
  
  
  
}
