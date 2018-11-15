package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.devices.Status;

public class Thermostat extends Device {
  private final Hub aMed;
  private Status status = Status.NORMAL;
  private Temperature setPoint;

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
    return "Thermostat id " + super.getIdentifier().toString();
  }

  public void setTemp(Temperature t) {
    setPoint = t;
    aMed.alert(this, "Setting temp to " + t.getTemperature());
    status = Status.NORMAL;
  }
}