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
		if(model != null && validateUser(model)) {
			return true;
		}
		return false;
	}

	private boolean validateUser(UserLoginBindingModel model) {
		if(!userExists(model.getUsername())) {
			return false;
		}
		if(!validatePassword(model)) {
			return false;
		}
		return true;
	}

	private boolean userExists(String username) {
			if(userFactory.findUser(username) != null) {
				return true;
			}
			return false;
	}

	private boolean validatePassword(UserLoginBindingModel model) {
		User user = userFactory.findUser(model.getUsername());
		if(user.getPassword().equals(model.getPassword())) {
			return true;
		}
		return false;
	}
}
