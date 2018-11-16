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
    status = Status.OFF;
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
    if(isOn == true) {
      statusProper.setValue("ON");
    } else {
      statusProper.setValue("OFF");
    }
    aMed.alert(this, status + isOn);
  }

  @Override
  public String toString() {
    return "Smartplug id " + super.getIdentifier().toString()+" Status: "+status.name();
  }
  
  public Button getChangeStatus() {
    return changeStatus;
  }
  
  public StringProperty getStatusProper() {
    return statusProper;
  }

  @Override
  public void handle(ActionEvent e) {
    if(e.getSource() == changeStatus && isOn == false) {
      isOn = true;
      status = Status.ON;
      statusProper.setValue("ON");
      changeStatus.setText("Turn off");
    } else {
      isOn = false;
      status = Status.OFF;
      statusProper.setValue("OFF");
      changeStatus.setText("Turn on");
    }
    
  }
}
