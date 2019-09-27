package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.entities.User;
import domain.enums.Role;
import domain.models.service.UserServiceModel;
import junit.framework.Assert;

public class UserFactoryTest {

	private UserFactory userFactory;
	private Connection connection;
	
	@Before
	public void initialize() {
		this.userFactory = new UserFactory();
		UserServiceModel user1 =
				new UserServiceModel(
						"Test1", "123456789",
						Role.ROLE_USER);
		this.userFactory.addUser(user1);
		this.connection = new DatabaseConnector().sqlConnection();
	}
	
	@Test
	public void testIfFindUserReturnsUserCorrectly() {
		User user =
				this.userFactory.findUser("Test1");
		Assert.assertEquals("Test1", user.getUsername());
		Assert.assertEquals("123456789", user.getPassword());
		Assert.assertEquals("ROLE_USER", user.getRoles());
	}
	
	@Test
	public void testIfFindUserReturnsNullOnNonExistingUser() {
		User user =
				this.userFactory.findUser("Test911");
		Assert.assertEquals(null, user);
	}
	
	@Test
	public void testIfGetAllUsersReturnsAllUsers() throws SQLException {
		ArrayList<User> users =
				this.userFactory.getAllUsers();
		PreparedStatement stmt = 
				this.connection
					.prepareStatement("select count(*) from users");
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		Assert.assertEquals(count, users.size());
	}
	
	@Test
	public void addUserAddsUserToDBCorrectly() {
		UserServiceModel user2 = new UserServiceModel(
				"Test2", "987654321", Role.ROLE_USER);
		this.userFactory.addUser(user2);
		User user = this.userFactory.findUser("Test2");
		Assert.assertEquals(user2.getUsername(), 
				user.getUsername());
		Assert.assertEquals(user.getPassword(),
				user2.getPassword());
		Assert.assertEquals(user.getRoles(), 
				user2.getRole().toString());
	}
	
	@Test
	public void updateUserUpdatesCorrectly() {
		User user =
				this.userFactory.findUser("Test1");
		user.setPassword("1234567890");
		user.setRoles(Role.ROLE_EMPLOYEE.toString());
		boolean condition = this.userFactory.updateUser(user);
		User updatedUser =
				this.userFactory.findUser("Test1");
		Assert.assertTrue(condition);
		Assert.assertEquals("1234567890", updatedUser.getPassword());
		Assert.assertEquals("ROLE_EMPLOYEE", updatedUser.getRoles());
	}
	
	@Test
	public void deleteUserDeletesUsersCorrectly() throws SQLException {
		PreparedStatement stmt = 
				this.connection
					.prepareStatement("select count(*) from users");
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int countBefore = rs.getInt(1);
		User user = this.userFactory.findUser("Test1");
		boolean condition =
				this.userFactory.deleteUser(user.getUserID());
		ResultSet rs2 = stmt.executeQuery();
		rs2.next();
		int countAfter = rs2.getInt(1);
		Assert.assertTrue(condition);
		Assert.assertEquals(countBefore-1, countAfter);
	}
	
	@After
	public void cleanDB() {
		if(userFactory.findUser("Test1") != null) {
			userFactory.deleteUser(
					userFactory.findUser("Test1")
					.getUserID());
		}
		if(userFactory.findUser("Test2") != null) {
			userFactory.deleteUser(
					userFactory.findUser("Test2")
					.getUserID());
		}
	}
}
