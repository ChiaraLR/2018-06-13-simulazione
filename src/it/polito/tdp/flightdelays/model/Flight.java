package it.polito.tdp.flightdelays.model;

import java.time.LocalDateTime;

public class Flight {

	private int id;
	private Airline airline;
	private int flightNumber;
	private Airport originAirport;
	private Airport destinationAirport;
	private LocalDateTime scheduledDepartureDate;
	private LocalDateTime arrivalDate;
	private Double departureDelay;
	private Double arrivalDelay;
	private int airTime;
	private Double distance;
	
	public Flight(int id, Airline airline, int flightNumber, Airport originAirport, Airport destinationAirport,
			LocalDateTime scheduledDepartureDate, LocalDateTime arrivalDate, Double departureDelay, Double arrivalDelay,
			Double distance, int airTime) {
		this.id = id;
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		LocalDateTime tempDateTime = LocalDateTime.from(scheduledDepartureDate);
		LocalDateTime tempDateTime2 = LocalDateTime.from(arrivalDate);
		this.departureDelay = departureDelay;
		this.arrivalDelay = arrivalDelay;
		this.airTime = airTime;
		this.distance = distance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Airport getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public LocalDateTime getScheduledDepartureDate() {
		return scheduledDepartureDate;
	}

	public void setScheduledDepartureDate(LocalDateTime scheduledDepartureDate) {
		this.scheduledDepartureDate = scheduledDepartureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public int getAirTime() {
		return airTime;
	}

	public void setAirTime(int airTime) {
		this.airTime = airTime;
	}

	

	public Double getDepartureDelay() {
		return departureDelay;
	}

	public void setDepartureDelay(Double departureDelay) {
		this.departureDelay = departureDelay;
	}

	public Double getArrivalDelay() {
		return arrivalDelay;
	}

	public void setArrivalDelay(Double arrivalDelay) {
		this.arrivalDelay = arrivalDelay;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", airline=" + airline + ", flightNumber=" + flightNumber + ", originAirport="
				+ originAirport + ", destinationAirport=" + destinationAirport + ", scheduledDepartureDate="
				+ scheduledDepartureDate + ", arrivalDate=" + arrivalDate + ", departureDelay=" + departureDelay
				+ ", arrivalDelay=" + arrivalDelay + ", airTime=" + airTime + ", distance=" + distance + "]";
	}

}