package services;

import domain.entities.User;
import domain.models.binding.UserLoginBindingModel;
import utils.UserFactory;

public class LoginService {
	
	private UserFactory userFactory;
	
	public LoginService() {
		this.userFactory = new UserFactory();
	}
	
	public boolean loginUser(UserLoginBindingModel model) {
		if(validateUser(model)) {
			return true;
		}
		return false;
	}

	private boolean validateUser(UserLoginBindingModel model) {
		if(!validateUsername(model.getUsername())) {
			return false;
		}
		if(!validatePassword(model)) {
			return false;
		}
		return true;
	}

	private boolean validateUsername(String username) {
		try{
			userFactory.findUser(username);
			return true;
		}catch (NullPointerException e) {
			return false;
		}
	}

	private boolean validatePassword(UserLoginBindingModel model) {
		User user = userFactory.findUser(model.getUsername());
		if(user.getPassword().equals(model.getPassword())) {
			return true;
		}
		return false;
	}

}
