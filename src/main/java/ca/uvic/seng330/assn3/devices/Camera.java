package ca.uvic.seng330.assn3.devices;

import java.util.Date;

import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.devices.Status;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;


public class Camera extends Device implements EventHandler<ActionEvent> {

  private boolean isRecording;
  private int diskSize;
  public Button startRecording;
  public Button changeStatus;
  public Button openStream;
  private StringProperty Recording;
  private StringProperty statusProper;
  private Status status;
  private boolean stream;
  CameraStream ctc;

  private final Hub aMed;

  public Camera(Hub med) {
    super();
    aMed = med;
    diskSize = 100;
    status = Status.OFF;
    Recording = new SimpleStringProperty("Not recording");
    statusProper = new SimpleStringProperty(status.name());
    startRecording = new Button("Start");
    openStream = new Button("Open stream");
    changeStatus = new Button("Turn ON");
    changeStatus.setOnAction(this);
    ctc = new CameraStream();
    openStream.setOnAction(this);
    startRecording.setOnAction(this);
    stream = false;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      // in future, log this
    }
  }

  public String startup() {
    isRecording = false;
    return "started";
  }

  public void record() throws CameraFullException {
    isRecording = true;
    Recording.setValue("Recording");
    System.out.println("recording");
    aMed.alert(this, "Started recording");
    aMed.getLogs().add("INFO - CAMERA RECORDING STARTED: " + this.getIdentifier() + " @ " +new Date().toString());

    if (Math.random() * 1000 > diskSize) {
      aMed.getLogs().add("ALERT - CAMERA RECORDING HALTED - CAMERA FULL: " + this.getIdentifier() + " @ " +new Date().toString());

      throw new CameraFullException("Camera Full");
    }
  }
  
  public void stopRecording() {
    isRecording =  false;
    Recording.setValue("Not Recording");
    aMed.alert(this, "Stopped recording");
    aMed.getLogs().add("INFO - CAMERA RECORDING STOPPED: " + this.getIdentifier() + " @ " +new Date().toString());

  }

  @Override
  public Status getStatus() {
    return Status.ERROR;
  }
  
  public StringProperty getStatusProper() {
    return statusProper;
  }
  
  public void setStatusProper(Status status) {
    if(status == Status.OFF && this.status!=Status.OFF) {
      if(isRecording ==  true) {
        this.stopRecording();
      }
      startRecording.setText("Start");
      statusProper.setValue("OFF");
      this.status = Status.OFF;
      changeStatus.setText("Turn ON");
    }
  }
  
  public Button getStartRecording() {
    return startRecording;
  }
  
  public Button getChangeStatus() {
    return changeStatus;
  }
  public Button getOpenStream() {
    return openStream;
  }
  

  @Override
  public String toString() {
    return "Camera id " + super.getIdentifier().toString() + " Status: " + super.getStatus().name();
  }
  
  @Override
  public void handle(ActionEvent e) {
    if (e.getSource() == startRecording && isRecording != true && status == Status.NORMAL) {
      try {
        this.record();
        startRecording.setText("Stop");
      } catch (CameraFullException e1) {
        // TODO Auto-generated catch block
        aMed.log("");
      }
    } else if (e.getSource() == startRecording && isRecording == true && status == Status.NORMAL) {
      this.stopRecording();
      startRecording.setText("Start");
    }
    
    if (e.getSource() == changeStatus && status == Status.OFF) {
      statusProper.setValue("ON");
      status = Status.NORMAL;
      changeStatus.setText("Turn OFF");
      aMed.getLogs().add("INFO - CAMERA STATUS ON ID: " + this.getIdentifier() + " @ " +new Date().toString());

    } else if (e.getSource() == changeStatus && status == Status.NORMAL){
      if (isRecording == true) {
        this.stopRecording();
        startRecording.setText("Start");
      }
      statusProper.setValue("OFF");
      status = Status.OFF;
      changeStatus.setText("Turn ON");
      aMed.getLogs().add("INFO - CAMERA STATUS OFF ID: " + this.getIdentifier() + " @ " +new Date().toString());

    }
    if(e.getSource() == openStream && status == Status.NORMAL) {
     Stage c = new Stage();
     CameraStream s = new CameraStream();
     aMed.getLogs().add("INFO - CAMERA STREAM ACCESSED: " + this.getIdentifier() + " @ " +new Date().toString());

     try {
      s.startStream(c);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    } 
  }
  
  public void userEntered() {
    for(Lightbulb l : aMed.getLightBulbs()) {
      if(l.getStatus() == Status.OFF) {
        l.setStatusProper(Status.ON);
        
      }
    }
  }
  
  public StringProperty getRecordingStatus() {
    return Recording;
  }
  
}
  
  

