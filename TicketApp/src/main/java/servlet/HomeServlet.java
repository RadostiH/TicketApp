package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.entities.Destination;
import utils.DestinationFactory;

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DestinationFactory df;
	
	public HomeServlet() {
		this.df = new DestinationFactory();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("username") != null) {
			ArrayList<Destination> list = df.getAllDestinations();
			req.setAttribute("username", req.getSession().getAttribute("username"));
			req.setAttribute("list", list);
			req.getRequestDispatcher("\\jsp\\home.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("/TicketApp/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		resp.sendRedirect("/TicketApp/login");
	}
	
	
}
