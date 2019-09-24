import domain.models.binding.RegisterUserBindingModel;
import services.RegisterService;

public class Test {
	
	public static void main(String[] args) {
   	    RegisterUserBindingModel model = new RegisterUserBindingModel();
		model.setUsername("radoo");
		model.setPassword("123");
		model.setConfirmPassword("123");
  		RegisterService rs = new RegisterService();
  		System.out.println(rs.registerUser(model) + " HEY");
	}
}
