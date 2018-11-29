package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SmartPlug extends Device implements SwitchableDevice, EventHandler<ActionEvent> {

  private final Hub aMed;
  private boolean isOn = false;
  private StringProperty statusProper;
  private Status status;
  private Button changeStatus;

  public SmartPlug(Hub med) {
    super();
    aMed = med;
    isOn = false;
    changeStatus = new Button("Turn on");
    changeStatus.setOnAction(this);
    status = super.getStatus();
    statusProper = new SimpleStringProperty("OFF");
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void toggle() {
    isOn = !isOn;
    String status = "plug is now ";
    if (isOn == true) {
      statusProper.setValue("ON");
      super.setStatus(Status.ON);
    } else {
      statusProper.setValue("OFF");
      super.setStatus(Status.OFF);
    }
    aMed.alert(this, status + isOn);
  }

  @Override
  public String toString() {
    return "Smartplug id " + super.getIdentifier().toString() + " Status: " + status.name();
  }
  
  public Button getChangeStatus() {
    return changeStatus;
  }
  
  public StringProperty getStatusProper() {
   // statusProper.setValue(super.getStatus().name());
    return statusProper;
  }
  
  public void setStatusProper(Status status) {
    if(status == Status.OFF) {
    this.status = status;
    isOn =  false;
    statusProper.setValue(status.name());
    changeStatus.setText("Turn on");
    }
  }

  @Override
  public void handle(ActionEvent e) {
    if (e.getSource() == changeStatus && isOn == false) {
      isOn = true;
      status = Status.ON;
      super.setStatus(Status.ON);
      statusProper.setValue("ON");
      changeStatus.setText("Turn off");
      String Status = "SmartPlug is now ";
      aMed.alert(this, Status + this.status.name());
    } else {
      isOn = false;
      status = Status.OFF;
      super.setStatus(Status.OFF);
      statusProper.setValue("OFF");
      changeStatus.setText("Turn on");
      String Status = "SmartPlug is now ";
      aMed.alert(this, Status + this.status.name());
    }
    
  }
}
