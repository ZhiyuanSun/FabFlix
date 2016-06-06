package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.connect;
import queries.Query;

/**
 * Servlet implementation class AddStar
 */
@WebServlet("/AddStar")
public class AddStar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = "";
		String lastName = "";
		String dob = "";
		String photo_url="";
		
		firstName=(String) request.getParameter("first_name");
		lastName=(String) request.getParameter("last_name");
		dob=(String) request.getParameter("date_of_birth");		
		photo_url=(String) request.getParameter("photo_url");	
		
		if (lastName.isEmpty()) 
		{
			lastName = firstName; 
			firstName = "";
		}


		try {
			
			connection=connect.check(connection);
			
			if(Query.addStar(firstName, lastName, dob, photo_url, connection)==1)
				request.setAttribute("star_result", "Star Adding Successful!");
			else
				request.setAttribute("star_result", "Star Adding Failed!");
			
			request.getRequestDispatcher("addStar.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
