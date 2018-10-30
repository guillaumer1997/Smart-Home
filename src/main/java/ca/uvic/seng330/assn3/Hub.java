package ca.uvic.seng330.assn3;


import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ca.uvic.seng330.assn3.devices.Device;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Hub implements Mediator {

	private HashMap<UUID, Device> devices;           //a client has a hub and a hub has devices
	private final Logger logger = LoggerFactory.getLogger(Hub.class);
	private HashMap<String, String> logs = new HashMap<String, String>();
	private Client c;
	
	
	public Hub() {
		devices = new HashMap<UUID, Device>();
		
		
	}
	
	
	public void startup() {
		logger.info("System Booting. @{} ", java.time.Clock.systemUTC().instant().toString());
		
		
	}
	
	public void shutdown() {
		logger.info("System going Offline. @{} ", java.time.Clock.systemUTC().instant().toString());
	}
	
	
	
	public void register(Device d) throws HubRegistrationException{
		if (devices.put(d.getIdentifier(),d) == null) {
			
			logger.error("device {} was not able to be found for registration", d.getIdentifier());
			log("device" + d + "was not able to be found for registration", java.time.Clock.systemUTC().instant().toString());
			throw new HubRegistrationException("invalid registration of device + d.getIdentifier() + ");
		}
		logger.info("device {} was registered to {}", d.getIdentifier(), this.getClass().toString());
		log(("device" + d + "was unregistered from" + this), java.time.Clock.systemUTC().instant().toString());
	}
	
	
	
	
	public void log(String message, String date) {
		
		logs.put(message, date); //simply log a message with the date
								 //these can be accessed by the clients
		
	}
	
	public void alert(String message, Device d) { //if there is an alert send a json message to the Client and add to the clients log
	    try {
		JSONMessaging jMsg = new JSONMessaging(d,message);
	    } catch(JSONException e) {}
		logs.put(message, java.time.Clock.systemUTC().instant().toString());
		
		
		
	}
	
	public void unregister(Device d) throws HubRegistrationException {
		if(devices.remove(d.getIdentifier()) == null){
			logger.error("device {} was not able to be found", d.getIdentifier());
			log(("device" + d.getIdentifier() + " was not able to be found during unregistration"), java.time.Clock.systemUTC().instant().toString());
			throw new HubRegistrationException("invalid removal");
		}
		
		logger.info("device {} was removed from {}", d, this);
		log(("device" + d + "was unregistered from" + this), java.time.Clock.systemUTC().instant().toString());
	
	}

	@Override
	public HashMap<UUID, Device> getDevices() {
		
		return devices;
	}

		
	


}
