package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.models.binding.RegisterUserBindingModel;
import services.RegisterService;

public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 95320841950100733L;
	private RegisterService registerService;
	
	public RegisterServlet() {
		this.registerService = new RegisterService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("\\jsp\\register.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		RegisterUserBindingModel model = getData(request);
 		registerService.registerUser(model);
 		response.sendRedirect("/TicketApp/home");
	}

	private RegisterUserBindingModel getData(HttpServletRequest request) {
		RegisterUserBindingModel model = new RegisterUserBindingModel();
		model.setUsername(request.getParameter("uname"));
		model.setPassword(request.getParameter("psw"));
		model.setConfirmPassword(request.getParameter("confPsw"));
		return model;
	}
}
