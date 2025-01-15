package model;

public class Seat
{
	private int Seat_ID;
	private int Flight_ID;

	private int Seat_No;
	private String Seat_Class;
	private String Seat_Status;
	
	public int getSeat_ID() {
		return Seat_ID;
	}
	public void setSeat_ID(int seat_ID) {
		Seat_ID = seat_ID;
	}
	public int getFlight_ID() {
		return Flight_ID;
	}
	public void setFlight_ID(int flight_ID)
	{
		Flight_ID = flight_ID;
	}
	public int getSeat_No() {
		return Seat_No;
	}
	public void setSeat_No(int seat_No) {
		Seat_No = seat_No;
	}
	public String getSeat_Class() {
		return Seat_Class;
	}
	public void setSeat_Class(String seat_Class) {
		Seat_Class = seat_Class;
	}
	public String getSeat_Status()
	{
		return Seat_Status;
	}
	public void setSeat_Status(String seat_Status) {
		Seat_Status = seat_Status;
	}
	

}
