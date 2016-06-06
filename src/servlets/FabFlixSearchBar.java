package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.connect;
import queries.Query;
import data_beans.Movie;

@WebServlet("/FabFlixSearchBar")
public class FabFlixSearchBar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			connection=connect.check(connection);

			String movieSearch = request.getParameter("searchText");

			if (movieSearch.trim() == null) {
				return;
			}

			String[] keywords = movieSearch.split(" ");

			ArrayList<Movie> movies = Query.fastSearchMovies(keywords, connection);
			request.setAttribute("movieSearch", movieSearch);
			request.setAttribute("movies", movies);
			request.getRequestDispatcher("FabFlixAutocompleteDropdown.jsp").forward(request, response);

			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
