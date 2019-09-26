package services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.enums.Role;
import domain.models.binding.UserLoginBindingModel;
import domain.models.service.UserServiceModel;
import utils.UserFactory;

public class LoginServiceTest {

	private LoginService loginservice;
	private UserLoginBindingModel model;
	private UserServiceModel serviceModel;
	private UserFactory userFactory;
	
	@Before
	public void initialize() {
		this.loginservice = new LoginService();
		this.model = new UserLoginBindingModel();
		this.userFactory = new UserFactory();
		this.serviceModel = new UserServiceModel();
		serviceModel.setUsername("Test1");
		serviceModel.setPassword("12345678");
		serviceModel.setRole(Role.ROLE_USER);
		userFactory.addUser(serviceModel);
	}
	
	@Test
	public void loginLogsOnValidCredentials() {
		model.setUsername("Test1");
		model.setPassword("12345678");
		boolean condition = this.loginservice.loginUser(model);
		Assert.assertTrue(condition);
	}
	
	@Test
	public void loginFailsWhenNullUserIsPassed() {
		boolean condition = this.loginservice.loginUser(null);
		Assert.assertFalse(condition);
	}
	
	@Test
	public void loginFailsOnInvalidUsername(){
		model.setUsername("Test11");
		model.setPassword("12345678");
		boolean condition = this.loginservice.loginUser(model);
		Assert.assertFalse(condition);
	}
	
	@Test
	public void loginFailsOnInvalidPassword() {
		model.setUsername("Test1");
		model.setPassword("123456789");
		boolean condition = this.loginservice.loginUser(model);
		Assert.assertFalse(condition);
	}
	
	@After
	public void cleanDB() {
		if(this.userFactory.findUser("Test1") != null) {
			this.userFactory
			.deleteUser(this.userFactory
					.findUser("Test1").getUserID());
		}
	}
}
