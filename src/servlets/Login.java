package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import connect.connect;
import queries.Query;
import verify.VerifyUtils;
import data_beans.Cart;
import data_beans.Customer;


@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private Connection connection;
	private HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);
		/*if (!valid) {
			request.setAttribute("login_invalid", "Please fill the reCAPTCHA!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		else{*/
			try 
			{
				login(email,password, request, response);
			} 
			catch (Exception e) 
			{
				e.getMessage();
			}
		}
//	}

	private synchronized void login(String email, String password, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException 
	{	
		Customer currentCustomer = null;

		connection=connect.check(connection);

		currentCustomer = Query.login_auth(email, password, connection);

		if (currentCustomer != null)
		{    
			session = request.getSession(true);
			session.setAttribute("customer_loggedin", currentCustomer);//change here
			session.setAttribute("connection", connection);
			session.setAttribute("session_cart", new Cart());
			response.sendRedirect("FabFlixMain");
		}
		else
		{
			request.setAttribute("login_invalid", "Password and email does not match!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		if (connection != null && !connection.isClosed())
		{
			connection.close();
		}
	}
}
