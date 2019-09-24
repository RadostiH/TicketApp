package services;


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
		serviceModel.setRole("ROLE_USER");
		return serviceModel;
	}

	private boolean validateUser(RegisterUserBindingModel model) {
		if(this.userExists(model)) {
			return false;
		}
		if(!model.getPassword().equals(model.getConfirmPassword())) {
			return false;
		}
		return true;
	}

	private boolean userExists(RegisterUserBindingModel model) {
		if(this.userFactory.findUser(model.getUsername()) != null) {
			return true;
		}
		return false;
	}
}
