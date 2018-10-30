package ca.uvic.seng330.assn3.devices;

import java.util.UUID;

import org.json.JSONObject;
import java.util.ArrayList;


public interface Device {
		
	public boolean getPower();
	public void togglePower();
	public UUID getIdentifier() ;
	
	
	
	public Status getStatus();
	

}
