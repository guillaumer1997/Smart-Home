package ca.uvic.seng330.assn3;

import java.util.HashMap;
import java.util.UUID;
import org.json.JSONObject;
import ca.uvic.seng330.assn3.devices.Device;



public interface Mediator  {

	final UUID uuid = UUID.randomUUID();
	
	
	void register(Device d) throws HubRegistrationException;		
	void unregister(Device d) throws HubRegistrationException;
	void startup();
	void shutdown();
	void log(String message, String date);
	public void alert(String message, Device d);
	public HashMap<UUID, Device> getDevices();
}
