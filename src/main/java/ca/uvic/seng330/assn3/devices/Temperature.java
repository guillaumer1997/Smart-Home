package ca.uvic.seng330.assn3.devices;


public class Temperature{

	private double currTemp = 25.0;   // default to 25.0f
	private Unit unit = Unit.CELSIUS; // default to celsius
	private boolean power;
	
	public class TemperatureOutofBoundsException extends Exception {

		
		public TemperatureOutofBoundsException(String m) {
			
			super(m); //throws custom temp exception 
		}

		
	}
		 
	
	
	public enum Unit {
		FAHRENHEIT, CELSIUS;
		
		@Override
		public String toString() {
			
			return this.name();
			
		}
	}
	
	/*public Temperature() {
		
		this.currTemp = 0; //default
		this.unit = Unit.CELSIUS;
		
		
	}*/
	
	
	public Temperature(double temp, Unit u) throws TemperatureOutofBoundsException {
		if (temp > 100 || temp < -100) {
			System.out.println("inside Temperature");
			throw new TemperatureOutofBoundsException("that tempertaure is invalid (too high or too cold)");		
		}
		this.currTemp = temp;// decent default temperature
		this.unit = u;
	}
	
	public Temperature(double temp) throws TemperatureOutofBoundsException  {
		if (temp > 100 || temp < -100) {
			System.out.println("inside Temperature");
			throw new TemperatureOutofBoundsException("that tempertaure is invalid (too high or too cold)");		
		}
		this.currTemp = temp;
		this.unit = Unit.CELSIUS;
	}
	
	public Temperature() {
		
	}

	public void setNewTemp(Temperature temp) throws TemperatureOutofBoundsException {
		if (temp.getTemp() > 100 || temp.getTemp() < -100) {
			System.out.println("inside Temperature");
			throw new TemperatureOutofBoundsException("that tempertaure is invalid (too high or too cold)");
		}
		this.currTemp = temp.getTemp();
		
	}
	
		
	public double getTemp() {
		
		return this.currTemp;
		
	}
	
	/*public float tempInFareheit() {
		
		float tempTemp = currTemp;
		return ((tempTemp * (9.0f/5.0f)+32));
		
	}*/
	
}
