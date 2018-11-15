package ca.uvic.seng330.assn3;

import java.util.Map;
import java.util.UUID;
import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.users.*;

public interface Mediator {
  

  public void unregister(Device device) throws HubRegistrationException;

  public void unregister(UserInterface User) throws HubRegistrationException;

  //not in spec, do not test
  public void register(Device pDevice) throws HubRegistrationException;

  public void register(UserInterface User) throws HubRegistrationException;

  public void alert(Device pDevice, String pMessage);

  public Map<UUID, Device> getDevices();
  
}