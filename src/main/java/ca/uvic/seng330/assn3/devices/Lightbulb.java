package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Lightbulb extends Device implements SwitchableDevice,EventHandler<ActionEvent> {

  private boolean isOn = false;
  private final Hub aMed;
  private StringProperty statusProper;
  private Status status;
  private Button toggleButton;

  public Lightbulb(Hub pMed) {
    super();
    aMed = pMed;
    isOn = false;
    statusProper = new SimpleStringProperty("OFF");
    status = status.OFF;
    toggleButton = new Button("Turn ON");
    toggleButton.setOnAction(this);
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void toggle() {
    isOn = !isOn;
  }
  
  public void setStatusProper(Status status) {
    if(status == Status.OFF) {
    this.status = status;
    isOn = false;
    statusProper.setValue(status.name());
    toggleButton.setText("Turn ON");
    }
  }

  public boolean getCondition() {
    return isOn;
  }
  
  public StringProperty getStatusProper() {
    return statusProper;
  }

  @Override
  public String toString() {
    return "Lightbulb id " + super.getIdentifier().toString() + " Status: " + status.name();
  }
  
  public Button getToggleButton() {
    return toggleButton;
  }
  
  @Override
  public void handle(ActionEvent e) {
    if (e.getSource() == toggleButton && isOn == false) {
      this.toggle();
      status = Status.ON;
      statusProper.setValue("ON");
      toggleButton.setText("Turn OFF");
      String Status = "lightbulb is now ";
      aMed.alert(this, Status + this.status.name());
    } else {
      this.toggle();
      status = Status.OFF;
      statusProper.setValue("OFF");
      toggleButton.setText("Turn ON");
      String Status = "lightbulb is now ";
      aMed.alert(this, Status + this.status.name());
    }
    
  }
}