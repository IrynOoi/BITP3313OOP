package model;

public class Seat
{
	private int Seat_ID;
	private int Flight_ID;

	private String Seat_No;
	private String Seat_Class;
	private double price;
	
	public Seat() {
		
	}
	
	public Seat(String seat_No, String seat_Class, double price) {
		Seat_No = seat_No;
		Seat_Class = seat_Class;
		this.price = price;
	}
	
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
	public String getSeat_No() {
		return Seat_No;
	}
	public void setSeat_No(String seat_No) {
		Seat_No = seat_No;
	}
	public String getSeat_Class() {
		return Seat_Class;
	}
	public void setSeat_Class(String seat_Class) {
		Seat_Class = seat_Class;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
