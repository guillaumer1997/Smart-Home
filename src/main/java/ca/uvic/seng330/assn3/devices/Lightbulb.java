package ca.uvic.seng330.assn3.devices;
import java.util.UUID;
import ca.uvic.seng330.assn3.Mediator;

public class Lightbulb implements Device{

	private boolean toggle = false;
	protected Mediator med;
	private boolean power = false;
	
	protected Status status = Status.NORMAL;
	private UUID uuid;
	
	public Lightbulb() { 
		this.uuid = UUID.randomUUID();
	}
	
	public Lightbulb(Mediator m) {
		this.med = m;
		this.uuid = UUID.randomUUID();
	}
	
	public void toggle() { //simple keep track of the 
		toggle = !toggle;
		if (toggle) {
			this.med.alert("the Lightbulb has been switched off", this);
		}
		else {
			this.med.alert("the Lightbulb has been switched on", this);
		}
	}
	
	public boolean getCondition() {
		
		return toggle;
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
