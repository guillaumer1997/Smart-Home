package ca.uvic.seng330.assn3;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import ca.uvic.seng330.assn3.appMVC.MVCMain;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public abstract class TestFXBase extends ApplicationTest {

	
	@Before
	public void setUpClass() throws Exception {
		ApplicationTest.launch(MVCMain.class);	
		
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		stage.show();
	}
	
	
	public void afterEachTest() throws TimeoutException{
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Node> T find(final String query) {
		return (T) lookup(query).queryAll().iterator().next();
	}
	
	
	
	
}
