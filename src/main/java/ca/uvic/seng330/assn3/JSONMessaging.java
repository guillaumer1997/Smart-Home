package ca.uvic.seng330.assn3;

import org.json.JSONException;
import org.json.JSONObject;
import ca.uvic.seng330.assn3.devices.Device;



public class JSONMessaging extends JSONObject {
	
	public JSONMessaging(Device d, String m) throws JSONException{
		
		super();
		super.put("msg_id", 1);
	    super.put("node_id", d.getIdentifier());
	    super.put("status", d.getStatus());
	    super.put("payload",  m);
	    super.put("created_at", java.time.Clock.systemUTC().instant().toString());
		
	}
	
	public JSONObject invoke() {
		return this;
		
	}
}
