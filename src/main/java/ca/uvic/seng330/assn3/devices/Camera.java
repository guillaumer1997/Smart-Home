package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.devices.Status;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;

public class Camera extends Device implements EventHandler<ActionEvent> {

  private boolean isRecording;
  private int diskSize;
  public Button startRecording;
  public Button changeStatus;
  private StringProperty Recording;
  private StringProperty statusProper;
  private Status status;

  private final Hub aMed;

  public Camera(Hub med) {
    super();
    aMed = med;
    diskSize = 999;
    status = Status.OFF;
    Recording = new SimpleStringProperty("Not recording");
    statusProper = new SimpleStringProperty(status.name());
    startRecording = new Button("Start");
    changeStatus = new Button("Turn ON");
    changeStatus.setOnAction(this);
    startRecording.setOnAction(this);
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
    if (Math.random() * 1000 > diskSize) {
      throw new CameraFullException("Camera Full");
    }
  }
  
  public void stopRecording() {
    isRecording =  false;
    Recording.setValue("Not Recording");
    aMed.alert(this, "Stopped recording");
  }

  @Override
  public Status getStatus() {
    return Status.ERROR;
  }
  
  public StringProperty getStatusProper() {
    return statusProper;
  }
  
  public Button getStartRecording() {
    return startRecording;
  }
  
  public Button getChangeStatus() {
    return changeStatus;
  }
  

  @Override
  public String toString() {
    return "Camera id " + super.getIdentifier().toString() + " Status: " + status.name();
  }
  
  @Override
  public void handle(ActionEvent e) {
    if (e.getSource() == startRecording && isRecording != true && status == Status.NORMAL) {
      try {
        this.record();
        startRecording.setText("Stop");
      } catch (CameraFullException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    } else if (e.getSource() == startRecording && isRecording == true && status == Status.NORMAL) {
      this.stopRecording();
      startRecording.setText("Start");
    }
    
    if (e.getSource() == changeStatus && status == Status.OFF) {
      statusProper.setValue("ON");
      status = Status.NORMAL;
      changeStatus.setText("Turn off");
    } else if (e.getSource() == changeStatus && status == Status.NORMAL){
      if (isRecording == true) {
        this.stopRecording();
        startRecording.setText("Start");
      }
      statusProper.setValue("OFF");
      status = Status.OFF;
      changeStatus.setText("Turn on");
    }
  }
  
  public StringProperty getRecordingStatus() {
    return Recording;
  }
  
}
  
  

