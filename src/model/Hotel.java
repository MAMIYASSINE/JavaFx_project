package model;

public class Hotel {

	private int hotelId;
    private String name;
    private String address;
    private double pricePerRoom;

    public Hotel(int hotelId, String name, String address, double pricePerRoom) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.pricePerRoom = pricePerRoom;
    }

	public Hotel(String name, String address, double pricePerRoom) {
		super();
		this.name = name;
		this.address = address;
		this.pricePerRoom = pricePerRoom;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getPricePerRoom() {
		return pricePerRoom;
	}

	public void setPricePerRoom(double pricePerRoom) {
		this.pricePerRoom = pricePerRoom;
	}

}
