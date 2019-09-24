package domain.entities;

public class Destination {
	
	private int id;
	private String startPoint;
	private String endPoint;
	private String departure;
	private String arrival;
	private int places;
	private double price;

	public Destination() {
	}
	
	public Destination(String startPoint, String endPoint, String departure, String arrival, int places, double price) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.departure = departure;
		this.arrival = arrival;
		this.places = places;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStartPoint() {
		return startPoint;
	}
	
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	
	public String getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	
	public String getDeparture() {
		return departure;
	}
	
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
	public String getArrival() {
		return arrival;
	}
	
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	
	public int getPlaces() {
		return places;
	}
	
	public void setPlaces(int places) {
		this.places = places;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
