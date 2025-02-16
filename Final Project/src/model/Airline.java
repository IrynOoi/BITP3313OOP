package model;

public class Airline 
{
	private int AirlineID;
	private String AirlineName;
	private String Headquaters;
	private String Status;
	
	   public Airline(String name) {
	        this.AirlineName = name;
	    }
	public int getAirlineID() {
		return AirlineID;
	}
	public void setAirlineID(int airlineID) {
		AirlineID = airlineID;
	}
	public String getName() {
		return AirlineName;
	}
	public void setName(String airlineName) {
		AirlineName = airlineName;
	}
	public String getHeadquaters() {
		return Headquaters;
	}
	public void setHeadquaters(String headquaters) {
		Headquaters = headquaters;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	

}
