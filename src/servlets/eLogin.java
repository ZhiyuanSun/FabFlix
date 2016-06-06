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

/**
 * Servlet implementation class eLogin
 */
@WebServlet("/_dashboard")
public class eLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection connection;
	private HttpSession session;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("employeeslogin.jsp").forward(request, response);
		
		//get connection to the database
		//insert a new star
		//show metadata
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try 
		{
			elogin(email,password, request, response);
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	private synchronized void elogin(String email, String password, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException 
	{	
		Boolean currentEmployee = false;
		
        connection=connect.check(connection);

        currentEmployee = Query.elogin_auth(email, password, connection);
        
		if (currentEmployee)
		{    
			session = request.getSession(true);
			session.setAttribute("employees_loggedin", currentEmployee);//change here
			session.setAttribute("connection", connection);
			response.sendRedirect("DBControl.jsp");
		}
		else
		{
			request.setAttribute("elogin_invalid", "Password and email does not match!");
			request.getRequestDispatcher("employeeslogin.jsp").forward(request, response);
		}
				
		if (connection != null && !connection.isClosed())
		{
			connection.close();
		}
	}
}

