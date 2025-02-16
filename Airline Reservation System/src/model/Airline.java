package model;

public class Airline {
	private int Airline_ID;
	private String Airline_Name;
	private String Headquarters;
	private String Status;
	
	public Airline(String Airline_Name ) {
		// TODO Auto-generated constructor stub
		this.Airline_Name=Airline_Name;
	}
	public Airline() {
		// TODO Auto-generated constructor stub
	}
	public int getAirline_ID() {
		return Airline_ID;
	}
	public void setAirline_ID(int airlineID) {
		Airline_ID = airlineID;
	}
	public String getHeadquarters() {
		return Headquarters;
	}
	public void setHeadquarters(String headquarters) {
		Headquarters = headquarters;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

	public String getAirline_Name() {
		return Airline_Name;
	}
	public void setAirline_Name(String airline_Name) {
		Airline_Name = airline_Name;
	}
	
	

}
