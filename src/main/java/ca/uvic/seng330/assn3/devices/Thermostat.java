package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.devices.Status;
import ca.uvic.seng330.assn3.devices.Temperature.TemperatureOutofBoundsException;
import ca.uvic.seng330.assn3.devices.Temperature.Unit;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Thermostat extends Device implements EventHandler<ActionEvent>{
  private final Hub aMed;
  private Status status = Status.OFF;
  private StringProperty properStat;
  private StringProperty properTemp;
  private boolean isOn;
  private Temperature setPoint;
  private Button toggleButton;
  private Button switchUnit;
  private Button setButton;
  private TextField newTemp;

  {
    try {
      setPoint = new Temperature(72, Temperature.Unit.FAHRENHEIT);
    } catch (Temperature.TemperatureOutofBoundsException e) {
      e.printStackTrace();
    }
  }

  public Thermostat(Hub mediator) {
    super();
    this.aMed = mediator;
    toggleButton = new Button("Turn ON");
    switchUnit = new Button("Switch Unit");
    setButton =  new Button("set Temperature");
    newTemp = new TextField();
    setButton.setOnAction(this);
    isOn = false;
    toggleButton.setOnAction(this);
    switchUnit.setOnAction(this);
    properStat = new SimpleStringProperty("OFF");
    properTemp = new SimpleStringProperty("NONE");
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Status getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "Thermostat id " + super.getIdentifier().toString() +  " Status: " + status.name();
  }

  public void setTemp(Temperature t) {
    setPoint = t;
    aMed.alert(this, "Setting temp to " + t.getTemperature());
    status = Status.NORMAL;
  }
  
  public Button getToggleButton() {
    return toggleButton;
  }
  
  public Button getSwitchUnit() {
    return switchUnit;
  }
  
  public Button getSetButton() {
    return setButton;
  }
  
  public TextField getNewTemp() {
    return newTemp;
  }
  
  public StringProperty getStatusProper() {
    return properStat;
  }
  
  public StringProperty getTempProper() {
    return properTemp;
  }
  
  public void setStatusProper(Status status) {
    if(status == Status.OFF) {
      properStat.setValue(status.name());
      this.status = status;
      properTemp.setValue("NONE");
      toggleButton.setText("Turn ON");
      isOn = false;
    }
  }


  @Override
  public void handle(ActionEvent event) {
    if(event.getSource() == toggleButton && isOn == false) {
      isOn = true;
      status = Status.ON;
      properStat.setValue("ON");
      properTemp.setValue(setPoint.getUnit().toString() + " : " + Double.toString(setPoint.getTemperature()));
      toggleButton.setText("Turn OFF");
      String Status = "Thermostat is now ";
      aMed.alert(this, Status + this.status.name());
      
    } else if(event.getSource() == toggleButton && isOn == true) {
      isOn = false;
      status = Status.OFF;
      properStat.setValue("OFF");
      properTemp.setValue("NONE");
      toggleButton.setText("Turn ON");
      String Status = "Thermostat is now ";
      aMed.alert(this, Status + this.status.name());
    }
    if(event.getSource() == switchUnit && isOn == true) {
      this.setPoint.toggleUnit();
      properTemp.setValue(setPoint.getUnit().toString() + " : " + Double.toString(setPoint.getTemperature()));
    }
    if(event.getSource() == setButton && isOn == true) {
      try {
        setTemp(new Temperature(Double.valueOf(newTemp.getText()), setPoint.getUnit()));
        properTemp.setValue(setPoint.getUnit().toString() + " : " + Double.toString(setPoint.getTemperature()));
        newTemp.setText("");
      } catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (TemperatureOutofBoundsException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    
    // TODO Auto-generated method stub
    
  }
}