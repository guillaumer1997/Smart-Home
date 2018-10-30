package ca.uvic.seng330.assn3.devices;
import java.util.UUID;
import ca.uvic.seng330.assn3.Client;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.devices.Temperature.TemperatureOutofBoundsException;
import ca.uvic.seng330.assn3.devices.Temperature.Unit;

public class Thermostat implements Device{

	
	protected Mediator med;
	
	protected Status status = Status.NORMAL;
	private Temperature temp;
	
	private boolean toggle = false;
	private boolean power = false;
	private Client c;
	private UUID uuid;
	
	
	
	public Thermostat() {
		
		this.uuid = UUID.randomUUID();
		
	}
	
	public Thermostat(Mediator m) {
		
		this.uuid = UUID.randomUUID();
	}
	
	
	public void setTemp(Temperature aTemp)  {	
		
		
	}
	
	public void toggle() {
		toggle = !toggle;
		if (toggle) {
			this.med.alert("the Thermostat has been switched off", this);
		}
		else {
			this.med.alert("the Thermostat has been switched on", this);
		}
	}

	@Override
	public UUID getIdentifier() {
		return this.uuid;
	}

	@Override
	public Status getStatus() {
		return this.status;
	}

	@Override
	public boolean getPower() {
		return this.power;
	}

	@Override
	public void togglePower() {
		this.power = !this.power;
		
	}
	
	
}
