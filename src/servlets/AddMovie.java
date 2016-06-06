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
 * Servlet implementation class AddMovie
 */
@WebServlet("/AddMovie")
public class AddMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovie() {
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
		String genre = "";
		String director="";
		int year;
		String title="";
		String movie_result;
		
		firstName=(String) request.getParameter("first_name");
		lastName=(String) request.getParameter("last_name");
		year=Integer.parseInt((String) request.getParameter("year"));		
		title=(String) request.getParameter("title");	
		genre=(String) request.getParameter("genre");	
		director=(String) request.getParameter("director");	
		
		try {			
			connection=connect.check(connection);
			
			movie_result=Query.addMovie(title, year, director, firstName,lastName, genre, connection);
			request.setAttribute("movie_result", movie_result);
			request.getRequestDispatcher("addMovie.jsp").forward(request, response);
			
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
