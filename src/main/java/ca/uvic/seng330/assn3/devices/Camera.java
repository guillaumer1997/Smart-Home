package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.Hub;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.devices.Status;

public class Camera extends Device {

  private boolean isRecording;
  private int diskSize;

  private final Hub aMed;

  public Camera(Hub med) {
    super();
    aMed = med;
    diskSize = 999;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      // in future, log this
    }
  }

  public String startup() {
    isRecording = false;
    return "started";
  }

  public void record() throws CameraFullException {
    isRecording = true;
    aMed.alert(this, "Started recording");
    if(Math.random()*1000 > diskSize) {
      throw new CameraFullException("Camera Full");
    }
  }

  @Override
  public Status getStatus() {
    return Status.ERROR;
  }

  @Override
  public String toString() {
    return "Camera id " + super.getIdentifier().toString();
  }
}
