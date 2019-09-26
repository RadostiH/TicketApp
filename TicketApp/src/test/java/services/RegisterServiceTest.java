package services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.models.binding.RegisterUserBindingModel;
import domain.models.service.UserServiceModel;
import junit.framework.Assert;
import utils.UserFactory;


public class RegisterServiceTest {

	private RegisterService registerService;
	private RegisterUserBindingModel model;
	
	@Before
	public void initialize() {
		registerService = new RegisterService();
		model = new RegisterUserBindingModel();
	}
	
	@Test
	public void testIfRegisterRegistratesUsers() {
		this.model.setUsername("Test1");
		this.model.setPassword("123456789");
		this.model.setConfirmPassword("123456789");
		boolean condition = this.registerService.registerUser(model);
		Assert.assertTrue(condition);
	}
	
	@Test
	public void registerFailsOnExistingUsername() {
		this.model.setUsername("radoiko");
		this.model.setPassword("123456789");
		this.model.setConfirmPassword("123456789");
		boolean condition = this.registerService.registerUser(model);
		Assert.assertFalse(condition);
	}
	
	@Test
	public void registerFailsOnNotMatchingPasswords() {
		this.model.setUsername("Test1");
		this.model.setPassword("helloWorld");
		this.model.setConfirmPassword("123456789");
		boolean condition = this.registerService.registerUser(model);
		Assert.assertFalse(condition);
	}
	
	@Test
	public void registerFailsIfNullIsPassed() {
		boolean condition = this.registerService.registerUser(null);
		Assert.assertFalse(condition);
	}
	
	@Test
	public void registerFailsOnUsernameLessThanFourChars() {
		this.model.setUsername("ABC");
		this.model.setPassword("123456789");
		this.model.setConfirmPassword("123456789");
		boolean condition = this.registerService.registerUser(model);
		Assert.assertFalse(condition);
	}
	
	@Test
	public void registerFailsOnPasswordLessThanEightChars() {
		this.model.setUsername("Test1");
		this.model.setPassword("1234567");
		this.model.setConfirmPassword("1234567");
		boolean condition = this.registerService.registerUser(model);
		Assert.assertFalse(condition);
	}
	
	@Test
	public void registerMapsUserCorrectlly() 
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		this.model.setUsername("Test1");
		this.model.setPassword("123456789");
		this.model.setConfirmPassword("123456789");
		Method mapUserModel = 
				RegisterService.class.
				getDeclaredMethod("mapUserModel", RegisterUserBindingModel.class);
		mapUserModel.setAccessible(true);
		UserServiceModel serviceModel = 
				(UserServiceModel) mapUserModel.invoke(registerService, model);
		Assert.assertEquals(model.getUsername(), serviceModel.getUsername());
		Assert.assertEquals(model.getPassword(), serviceModel.getPassword());
		Assert.assertEquals("ROLE_USER", serviceModel.getRole());
	}
	
	@After
	public void cleanDB() {
		UserFactory userFactory = new UserFactory();
		if(userFactory.findUser("Test1") != null) {
			userFactory.deleteUser(userFactory.findUser("Test1").getUserID());
		}
	}

}
