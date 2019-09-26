package services;


import domain.enums.Role;
import domain.models.binding.RegisterUserBindingModel;
import domain.models.service.UserServiceModel;
import utils.UserFactory;

public class RegisterService {
	
	private UserFactory userFactory;
	
	public RegisterService() {
		userFactory = new UserFactory();
	}
	
	public boolean registerUser(RegisterUserBindingModel model) {
		if(this.validateUser(model)) {
			UserServiceModel serviceModel = this.mapUserModel(model);
			this.userFactory.addUser(serviceModel);
			return true;
		}
		return false;
	}
	
	private UserServiceModel mapUserModel(
			RegisterUserBindingModel bindingModel) {
		UserServiceModel serviceModel = new UserServiceModel();
		serviceModel.setUsername(bindingModel.getUsername());
		serviceModel.setPassword(bindingModel.getPassword());
		serviceModel.setRole(Role.ROLE_USER);
		return serviceModel;
	}

	private boolean validateUser(RegisterUserBindingModel model) {
		if(model == null || this.userExists(model) || model.getUsername().length() < 4) {
			return false;
		}
		if(!model.getPassword().equals(model.getConfirmPassword())
				|| model.getPassword().trim().length() < 8
				|| model.getPassword() == null) {
			return false;
		}
		return true;
	}

	private boolean userExists(RegisterUserBindingModel model) {
		if(userFactory.findUser(model.getUsername()) != null) {
			return true;
		}
		return false;
	}
}
