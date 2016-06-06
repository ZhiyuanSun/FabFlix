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
import data_beans.Star;

@WebServlet("/FabFlixStarDetails")
public class FabFlixStarDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private Connection connection;
	
    public FabFlixStarDetails() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
				
		try
		{	
			connection=connect.check(connection);
			// Find star based on id
			String starId = request.getParameter("starid");
			Star star = Query.getStar(starId, connection);
			
			request.setAttribute("star", star);
			request.setAttribute("movies", star.getMovies());
			request.getRequestDispatcher("FabFlixStarDetails.jsp").forward(request, response);
			
			if (connection != null && !connection.isClosed())
			{
				connection.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
