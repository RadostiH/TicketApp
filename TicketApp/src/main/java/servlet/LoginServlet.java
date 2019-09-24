package servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.models.binding.UserLoginBindingModel;
import services.LoginService;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 5055275405205348066L;
	private LoginService loginService;
	
	public LoginServlet() {
		this.loginService = new LoginService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("\\jsp\\index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("login") != null) {
			UserLoginBindingModel model = new UserLoginBindingModel();
			model.setUsername(req.getParameter("uname"));
			model.setPassword(req.getParameter("psw"));
			if(this.loginService.loginUser(model)) {
				resp.sendRedirect("/TicketApp/home");
			}else {
				resp.sendRedirect("http://localhost:8080/TicketApp/login");
			}
		}else if(req.getParameter("register") != null){
			resp.sendRedirect("/TicketApp/register");
		}
	}
}