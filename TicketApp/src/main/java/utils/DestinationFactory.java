package utils;


import java.sql.*;
import java.util.ArrayList;

import domain.entities.Destination;
import domain.models.binding.DestinationBindingModel;

public class DestinationFactory {
	
	private Connection connection;
	
	public DestinationFactory() {
		this.connection = new DatabaseConnector().sqlConnection();
	}
	
	public Destination findDestination(String from, String to, String departure) {
		Destination destination = new Destination();
		try {
			String sql = "SELECT * FROM destinations WHERE start_point = ? "
					+ "AND end_point = ? "
					+ "AND departure = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, from);
			statement.setString(2, to);
			statement.setString(3, departure);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				destination = this.extractDestinationFromResultSet(rs);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return destination;
	}
	
	public ArrayList<Destination> findDestinationsByStartingPoint(String from) {
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		try {
			String sql = "SELECT * FROM destinations WHERE start_point = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, from);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				destinations.add(this.extractDestinationFromResultSet(rs));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return destinations;
	}
	
	public ArrayList<Destination> findDestinationsByEndPoint(String to) {
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		try {
			String sql = "SELECT * FROM destinations WHERE end_point = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, to);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				destinations.add(this.extractDestinationFromResultSet(rs));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return destinations;
	}
	
	public ArrayList<Destination> getAllDestinations() {
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM destinations");
			while(rs.next()) {
				destinations.add(this.extractDestinationFromResultSet(rs));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return destinations;
	}
	
	public boolean addDestination(DestinationBindingModel destination) {
	    try {
	        PreparedStatement ps = 
	        		this.connection.prepareStatement("INSERT INTO destinations"
	        				+ "(start_point, end_point, departure, arrival, places, price) "
	        				+ "VALUES (?, ?, ?, ?, ?, ?)");
	        ps.setString(1, destination.getStartPoint());
	        ps.setString(2, destination.getEndPoint());
	        ps.setString(3, destination.getDeparture());
	        ps.setString(4, destination.getArrival());
	        ps.setInt(5, destination.getPlaces());
	        ps.setDouble(6, destination.getPrice());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public boolean updateDestination(Destination destination) {
		try {
	        PreparedStatement ps = 
	        		this.connection.prepareStatement("UPDATE destinations SET"
	        				+ " start_point=?, end_point=?, departure=?, arrival=?, places=?, price=?  WHERE id = ?");
	        ps.setString(1, destination.getStartPoint());
	        ps.setString(2, destination.getStartPoint());
	        ps.setString(3, destination.getDeparture());
	        ps.setString(4, destination.getArrival());
	        ps.setInt(5, destination.getPlaces());
	        ps.setDouble(6, destination.getPrice());
	        ps.setInt(7, destination.getId());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public boolean deleteDestination(int id) {
	    try {
	        PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM destinations WHERE id = ?");
	        stmt.setInt(1, id);
	        int i = stmt.executeUpdate();
	      if(i == 1) {
	    return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	private Destination extractDestinationFromResultSet(ResultSet rs) throws SQLException {
		Destination destination = new Destination();
		destination.setId(rs.getInt("id"));
		destination.setDeparture(rs.getString("departure"));
		destination.setArrival(rs.getString("arrival"));
		destination.setStartPoint(rs.getString("start_point"));
		destination.setEndPoint(rs.getString("end_point"));
		destination.setPlaces(rs.getInt("places"));
		destination.setPrice(rs.getDouble("price"));
		return destination;
	}
}
