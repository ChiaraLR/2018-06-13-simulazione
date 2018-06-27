package it.polito.tdp.flightdelays.model;

import java.util.ArrayList;
import java.util.List;

public class Airline {
	
	private String id;
	private String name;
	private List<Flight> flights;
	
	public Airline(String id, String name) {
		this.id = id;
		this.name = name;
		this.flights= new ArrayList<Flight>();
	}
	

	public List<Flight> getFlights() {
		return flights;
	}


	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}


	public String getAirlineId() {
		return id;
	}

	public void setAirlineId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		
		return id + " " + name;
	}
}
