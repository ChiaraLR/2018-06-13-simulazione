package it.polito.tdp.flightdelays.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.flightdelays.model.AirportIdMap;
import it.polito.tdp.flightdelays.model.Flight;
import it.polito.tdp.flightdelays.model.FlightIdMap;
import it.polito.tdp.flightdelays.db.ConnectDB;
import it.polito.tdp.flightdelays.model.AirlineIdMap;
import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.Airport;


public class FlightDelaysDAO {

	public List<Airline> getAllAirlines(AirlineIdMap airlineIdMap) {
		String sql = "SELECT * FROM airlines";
		List<Airline> list = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Airline airline = new Airline(res.getString("ID"), res.getString("AIRLINE"));
				list.add(airlineIdMap.get(airline));
			}
			conn.close();
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	public List<Airport> getAllAirports(AirportIdMap airportIdMap) {
		String sql = "SELECT * FROM airports";
		List<Airport> list = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Airport airport = new Airport(res.getString("ID"), res.getString("AIRPORT"), res.getString("CITY"),
						res.getString("STATE"), res.getString("COUNTRY"),res.getDouble("LATITUDE"), res.getDouble("LONGITUDE")) ; 
				list.add(airportIdMap.get(airport));
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	


	public List<Flight> loadAllFlights(AirlineIdMap airlineIdMap, AirportIdMap airportIdMap, FlightIdMap routeIdMap) {
			String sql = "SELECT * FROM flights";
			List<Flight> list = new ArrayList<>();
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();
				
				int counter = 0;
				while (res.next()) {
					Airport sourceAirport = airportIdMap.get(res.getString("ORIGIN_AIRPORT_ID"));
					Airport destinationAirport = airportIdMap.get(res.getString("DESTINATION_AIRPORT_ID"));
					Airline airline = airlineIdMap.get(res.getString("AIRLINE"));
							
					Flight route = new Flight(counter, airline, res.getInt("FLIGHT_NUMBER"), sourceAirport, destinationAirport,
							res.getTimestamp("SCHEDULED_DEP_DATE").toLocalDateTime(),
							res.getTimestamp("ARRIVAL_DATE").toLocalDateTime(), res.getDouble("DEPARTURE_DELAY"),
							res.getDouble("ARRIVAL_DELAY"), res.getDouble("AIR_TIME"), res.getInt("DISTANCE"));
							
					list.add(routeIdMap.get(route));
					counter++;
					System.out.println(route);
					if (sourceAirport==null) {
						System.out.println("fonte non trovata");
					}
					sourceAirport.getFlights().add(routeIdMap.get(route));
					destinationAirport.getFlights().add(routeIdMap.get(route));
					airline.getFlights().add(routeIdMap.get(route));
				}	
				conn.close();
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}


	
		public List<Flight> getFlightsByAirline(Airline a, AirportIdMap airportIdMap, FlightIdMap routeIdMap) {
			String sql ="select * from flights " + 
					"where ID=?" ;
			List<Flight> list = new ArrayList<>();
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, a.getAirlineId());
				System.out.println(a);
				ResultSet res = st.executeQuery();
				
				int counter = 0;
				while (res.next()) {
					System.out.println("trovato");
					Airport sourceAirport = airportIdMap.get(res.getString("ORIGIN_AIRPORT_ID"));
					Airport destinationAirport = airportIdMap.get(res.getString("DESTINATION_AIRPORT_ID"));
					
					Flight route = new Flight(counter, a, res.getInt("FLIGHT_NUMBER"), sourceAirport, destinationAirport,
							res.getTimestamp("SCHEDULED_DEP_DATE").toLocalDateTime(),
							res.getTimestamp("ARRIVAL_DATE").toLocalDateTime(), res.getDouble("DEPARTURE_DELAY"),
							res.getDouble("ARRIVAL_DELAY"), res.getDouble("AIR_TIME"), res.getInt("DISTANCE"));	
					
					list.add(routeIdMap.get(route));
					counter++;
					
					sourceAirport.getFlights().add(routeIdMap.get(route));
					destinationAirport.getFlights().add(routeIdMap.get(route));
					
				}	
				conn.close();
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			}


		public double getMediaRitardi(Airport a1, Airport a2) {
			String sql="select AVG(DEPARTURE_DELAY) as r from flights " + 
					"where ORIGIN_AIRPORT_ID=? AND DESTINATION_AIRPORT_ID=?";
			Double ris = 0.0;
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, a1.getAirportId());
				st.setString(2, a2.getAirportId());
				ResultSet res = st.executeQuery();
				
				if (res.next()) {
					ris= res.getDouble("r");
				}
				conn.close();
				return ris;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		}
