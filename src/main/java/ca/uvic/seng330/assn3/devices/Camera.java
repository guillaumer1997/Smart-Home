package ca.uvic.seng330.assn3.devices;


import java.util.UUID;
import ca.uvic.seng330.assn3.Client;
import ca.uvic.seng330.assn3.Mediator;

public class Camera implements Device {
	 
	private boolean power = false;
	private boolean isRecording;
	private int diskSize;
	private Client c;
	private Mediator med;
	private UUID uuid;
	private Status status = Status.NORMAL;
	
	//protected Status status = Status.NORMAL;
	
	public Camera() {
		
		this.uuid = UUID.randomUUID();
	}
	
	public Camera(Mediator m) {
		this.med = m;
		this.uuid = UUID.randomUUID();
	}
	public Camera(int ds, Mediator m) {
		this.med = m;
		this.diskSize = ds;
		this.uuid = UUID.randomUUID();
		
	}
	public void setStatus(Status s) {
		this.status = s;
		
	}
	public int getDiskSize() {
		return this.diskSize;
	}
	
	public void setDiskSize(int s) {
		this.diskSize = s;
	}
	
	
	
	
	public void record() throws CameraFullException{
		if (diskSize >= 100) {
			System.out.println("inside record(). ");
			this.med.alert("Camera is full", this);
			throw new CameraFullException("Full");
		}
		isRecording = !isRecording;
			
	}
	
	public boolean recording() {	
		
		return this.isRecording; 
	}

	@Override
	public UUID getIdentifier() {
		return this.uuid;
	}

	public Client getClient() {
		return this.c;
	}

	@Override
	public Status getStatus() {
		return this.status ;
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
