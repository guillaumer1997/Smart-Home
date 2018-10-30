package ca.uvic.seng330.assn3.devices;




public enum Status {

	ERROR, SAFE, NORMAL;
		
	@Override
	public String toString() {
		
		return this.name();
		
	}
	
}
