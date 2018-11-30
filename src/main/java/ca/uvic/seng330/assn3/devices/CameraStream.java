package ca.uvic.seng330.assn3.devices;

import com.sun.glass.ui.Application.EventHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CameraStream {
  private Stage primaryStage;

  public void startStream(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    WebView webview = new WebView();

    webview.getEngine().loadContent(
      "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/fAWXVOGPKGA\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"
    );
    webview.setPrefSize(570, 330);

    this.primaryStage.setScene(new Scene(webview));
    this.primaryStage.show();
    primaryStage.setOnCloseRequest(e -> {
      webview.getEngine().load(null);;
    });
  }
  
  


}
