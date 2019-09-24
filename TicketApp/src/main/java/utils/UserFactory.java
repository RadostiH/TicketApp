package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.postgresql.util.PSQLException;
import domain.entities.User;
import domain.models.service.UserServiceModel;

public class UserFactory {
private Connection connection;
	
	public UserFactory() {
		this.connection = new DatabaseConnector().sqlConnection();
	}
	
	public User findUser(String username) throws NullPointerException {
		User user = new User();
		try {
			String sql ="SELECT * FROM users WHERE username = ?;";
			PreparedStatement statement = this.connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				user = this.extractUserFromResultSet(rs);
			}
		}catch (PSQLException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users");
			while(rs.next()) {
				users.add(this.extractUserFromResultSet(rs));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean addUser(UserServiceModel user) {
	    try {
	        PreparedStatement ps = 
	        		this.connection.prepareStatement("INSERT INTO users"
	        				+ "(username, password, roles)"
	        				+ "VALUES (?, ?, ?)");
	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getPassword());
	        ps.setString(3, user.getRole());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public boolean updateUser(User user) {
		try {
	        PreparedStatement ps = 
	        		this.connection.prepareStatement("UPDATE users SET"
	        				+ " username=?, password=?, roles=?  WHERE id = ?");
	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getPassword());
	        ps.setString(3, user.getRoles());
	        ps.setInt(4, user.getUserID());
	        int i = ps.executeUpdate();
	        if(i == 1) {
	        	return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public boolean deleteUser(int id) {
	    try {
	        PreparedStatement stmt = this.connection.prepareStatement("DELETE * FROM users WHERE id = ?");
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
	
	private User extractUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setRoles(rs.getString("roles"));
		return user;
	}

}
