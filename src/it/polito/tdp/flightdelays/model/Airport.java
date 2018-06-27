package it.polito.tdp.flightdelays.model;

import java.util.ArrayList;
import java.util.List;



public class Airport {

	private String id;
	private String name;
	private String city;
	private String state;
	private String country;
	private double latitude;
	private double longitude;
	
	private List<Flight> flights;
	
	public Airport(String id, String name, String city, String state, String country, double latitude,
			double longitude) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.flights= new ArrayList<Flight>();
	}

	
	public List<Flight> getFlights() {
		return flights;
	}


	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}


	public String getAirportId() {
		return id;
	}

	public void setAirportId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Airport [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
}
