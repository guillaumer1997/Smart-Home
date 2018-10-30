package ca.uvic.seng330.assn3;


import java.util.HashMap;

import org.json.JSONObject;
import ca.uvic.seng330.assn3.devices.Device;



public abstract class Client {

	protected Mediator med;
	private HashMap<String, String> logs = new HashMap<String, String>();
	private boolean hasMessage;
	private JSONMessaging jMsg;
	
	public Client(Mediator m) {
		this.med = m;
	}
	
	public HashMap<String, String> getLogs() {
		return this.logs;
	}
	
	public void selectDevice(Device d) {
		
		
	}
	
	public JSONObject notify(JSONMessaging jMsg) {
		
		return jMsg.invoke();
	}
	
	
}
