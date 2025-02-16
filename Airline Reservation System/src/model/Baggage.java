package model;

public class Baggage {
	private Integer baggageID;
	private int addOnWeight;
	private double addOnPrice;
	
	public Baggage() {
		
	}
	
	public Baggage(Integer baggageID, int addOnWeight) {
		this.baggageID = baggageID;
		this.addOnWeight = addOnWeight;
	}
	
	public Integer getBaggageID() {
		return baggageID;
	}
	public void setBaggageID(Integer baggageID) {
		this.baggageID = baggageID;
	}
	public int getAddOnWeight() {
		return addOnWeight;
	}
	public void setAddOnWeight(int addOnWeight) {
		this.addOnWeight = addOnWeight;
	}
	public double getAddOnPrice() {
		return addOnPrice;
	}
	public void setAddOnPrice(double addOnPrice) {
		this.addOnPrice = addOnPrice;
	}
}
