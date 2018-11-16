package ca.uvic.seng330.assn3;

import java.util.ResourceBundle;
import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.testfx.api.FxRobotException;

import ca.uvic.seng330.assn3.appMVC.MVCMain;
import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.users.Admin;
import ca.uvic.seng330.assn3.users.UserStatus;

//@RunWith (JUnitParamsRunner.class)
public class LoginTest extends TestFXBase{
	
	final String PASSWORD_PASSWORDFIELD_ID = "#password";
	final String USERNAME_TEXTFIELD_ID = "#username";
	final String CREATEADMIN_BUTTON_ID = "#AdminBut";
	final String NEWACCOUNT_BUTTON_ID = "#newAc";
	final String LOGIN_BUTTON_ID = "#LoginButton";
	
	final String VALID_USERNAME = "admin";
	final String VALID_PASSWORD = "admin";		
	
	@Test
	public void testAdminCreation() {
		Hub aMed = new Hub();
		
		clickOn(USERNAME_TEXTFIELD_ID);
		write("admin");
		clickOn(PASSWORD_PASSWORDFIELD_ID);
		write("admin");
		
		clickOn(CREATEADMIN_BUTTON_ID);
		//stageManager.switchScene("AdminFXML.fxml");
		
		assert(( aMed.Users.get(0)).getStatus() == (UserStatus.ADMIN) );	
		
	}
	
	
	@Test//(expected = (FxRobotException.class) )
	public void clickOnLoginButton() { 
		clickOn(LOGIN_BUTTON_ID);
	}
	
	@Test 
	public void clickOnCreateAdminButton() {	
		clickOn(CREATEADMIN_BUTTON_ID);
	}
	
	@Test 
	public void clickOnNewAccountButton() {
		clickOn(NEWACCOUNT_BUTTON_ID);
	}
	
	
	private void clearOutInputFields() {
		final int NUM_TO_ERASE = 20;
		clickOn(USERNAME_TEXTFIELD_ID).eraseText(NUM_TO_ERASE);
		clickOn(PASSWORD_PASSWORDFIELD_ID).eraseText(NUM_TO_ERASE);


	}
	
	
	
}
