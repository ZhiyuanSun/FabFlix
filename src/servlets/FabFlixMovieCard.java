package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.connect;
import queries.Query;
import data_beans.Movie;

@WebServlet("/FabFlixMovieCard")
public class FabFlixMovieCard extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private Connection connection;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{connection=connect.check(connection);
			
			String movieId = request.getParameter("movieid");
			
			ArrayList<Movie> movies = Query.search(movieId, "", "", "", "", "", "","", false, connection);
			
			if (movies.size() == 1)
			{
				Movie movie = movies.get(0);
				
				request.setAttribute("movie", movie);
				request.setAttribute("stars", movie.getStars());
				request.getRequestDispatcher("FabFlixMovieCard.jsp").forward(request, response);
				
			}
			else
			{
				response.getWriter().write("<link href=\"Style/movie_card.css\" rel=\"stylesheet\" /><p>No details found</p>");
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
