package it.polito.tdp.flightdelays.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {

	private List<Airport> airports;
	private List<Airline> airlines;

	
	private AirlineIdMap airlineIdMap;
	private AirportIdMap airportIdMap;
	private FlightIdMap flightIdMap;
	private FlightDelaysDAO dao;

	private SimpleDirectedWeightedGraph<Airport, DefaultWeightedEdge> graph;
	
	public Model() {
		this.dao= new FlightDelaysDAO();
		airlineIdMap= new AirlineIdMap();
		airportIdMap=new AirportIdMap();
		flightIdMap = new FlightIdMap();
		
		
	}
	
	public List<Airline> getAllAirlines(){
		return dao.getAllAirlines(airlineIdMap);
	}
	public List<Airport> getAllAirports(){
		return dao.getAllAirports(airportIdMap);
	}
	
	public void creaGrafo(Airline airline) {
		System.out.println("entro nel crea greafo");
		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		airports= dao.getAllAirports(airportIdMap);
		airlines= dao.getAllAirlines(airlineIdMap);
		Graphs.addAllVertices(graph, airports);
		System.out.println(graph.vertexSet());
	
		//List<Flight> flights = dao.getFlightsByAirline(airline, airportIdMap, flightIdMap);
		List<Flight> flights = dao.loadAllFlights(airlineIdMap, airportIdMap, flightIdMap);
		System.out.println(flights);
		for (Flight r :flights) {
			if ((r.getOriginAirport() != null) && (r.getDestinationAirport() != null)) {
				Airport a1 = airportIdMap.get(r.getOriginAirport());
				Airport a2 = airportIdMap.get(r.getDestinationAirport());

				if (a1 != null && a2 != null) {

					LatLng c1 = new LatLng(a1.getLatitude(), a1.getLongitude());
					LatLng c2 = new LatLng(a2.getLatitude(), a2.getLongitude());
					double distance = LatLngTool.distance(c1, c2, LengthUnit.KILOMETER);
					double delays = dao.getMediaRitardi(a1,a2);
					
					double peso= (delays/distance);

					Graphs.addEdge(graph, a1, a2, peso);
					 System.out.format("%s->%s %.0fkm\n", a1, a2, distance);

				}
			}
		}
	}
}
