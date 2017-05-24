package model;

public class Point {
	private int id;
	private float latitude;
	private float longitude;
	
	public Point(int id, float latitude, float longitude){
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Point(float latitude, float longitude){
		id = 0;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
}
