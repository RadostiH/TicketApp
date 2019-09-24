import domain.models.binding.UserLoginBindingModel;
import services.LoginService;
import utils.UserFactory;

public class Main {
	public static void main(String[] args) {
		UserLoginBindingModel model = new UserLoginBindingModel();
		LoginService loginService = new LoginService();
		UserFactory factory = new UserFactory();
		model.setUsername("radoiko");
		model.setPassword("rado1");
		System.out.println(loginService.loginUser(model));
		System.out.println(factory.findUser("radoo").toString());
	}
}
