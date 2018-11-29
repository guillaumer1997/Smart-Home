package ca.uvic.seng330.assn3.devices;

public class Temperature {

  public enum Unit {
    CELSIUS, FAHRENHEIT
  }

  private Unit unit = Unit.CELSIUS;
  private double temperature = 0.0;

  public Temperature(double temperature, Unit unit) throws TemperatureOutofBoundsException {
    if (temperature > 1000) {
      throw new TemperatureOutofBoundsException("Absurd temperature!");
    }

    this.temperature = temperature;
    this.unit = unit;
  }

  public double getTemperature() {
    return temperature;
  }

  public Unit getUnit() {
    return unit;
  }
  
  public void toggleUnit() {
    if(unit == Unit.CELSIUS) {
      temperature = (temperature * 9/5.0) +32;
      unit = Unit.FAHRENHEIT;
      
    } else if(unit == Unit.FAHRENHEIT) {
      temperature = (temperature-32) * 5/9.0;
      unit = Unit.CELSIUS;
      
    }
  }

  public class TemperatureOutofBoundsException extends Exception {
    public TemperatureOutofBoundsException(String s) {
      super(s);
    }
  }
}
