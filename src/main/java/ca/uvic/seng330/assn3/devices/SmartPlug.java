package ca.uvic.seng330.assn3.devices;
import java.util.UUID;
import ca.uvic.seng330.assn3.Mediator;

public class SmartPlug implements Device{
	
	//private boolean power;
	private boolean toggle = false; //default off
	protected Mediator med;
	private boolean power = false;
	
	protected Status status = Status.NORMAL;
	private UUID uuid;
	
	
	public SmartPlug() { 
		this.uuid = UUID.randomUUID();
	}
	
	public SmartPlug(Mediator m) {
		this.med = m;
		this.uuid = UUID.randomUUID();
	}
	
	
	public void toggle() {
		toggle = !toggle;
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
