package ca.uvic.seng330.assn3;

import java.io.IOException;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import ca.uvic.seng330.assn3.appMVC.AdminController;
import ca.uvic.seng330.assn3.appMVC.LoginController;
import ca.uvic.seng330.assn3.appMVC.MVCMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminUITest extends ApplicationTest{
  
  private Hub m;
  private AdminController c;
  
  
  @Override
  public void start(Stage stage) throws IOException {
    m = new Hub();
    c = new AdminController(m);
    Parent root = null;
    FXMLLoader loader = new FXMLLoader(MVCMain.class.getResource("AdminUI.fxml"));
    loader.setController(c);
    root = loader.load();
    Scene scene = new Scene(root); 
    stage.setScene(scene);
    stage.show();
  }
  
  
  @Test
  public void TestSomething() {
    doubleClickOn("#DeviceTab");
    WaitForAsyncUtils.waitForFxEvents();
  }
  
  
  

}
