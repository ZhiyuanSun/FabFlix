package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import connect.connect;
import data_beans.Movie;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public search() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			conn = connect.check(conn);
			String keyword = request.getParameter("keyword");
			PrintWriter out = response.getWriter();
			ArrayList<String> titleTokens = new ArrayList<String>(Arrays.asList(keyword.split(" ")));
			
			ArrayList<Movie> movies = fastSearchMoviestitle(titleTokens, conn);
			
			toApp(movies, out);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void toApp(ArrayList<Movie> movies, PrintWriter out) throws IOException {
		// TODO Auto-generated method stub
		try {
			JSONObject moviesj = new JSONObject();
			JSONArray array = new JSONArray();
			for (Movie movie : movies) {
				JSONObject moviej = new JSONObject();
				moviej.put("Id", movie.getId());
				moviej.put("title", movie.getTitle());
				moviej.put("year", movie.getYear());
				moviej.put("director", movie.getDirector());
				moviej.put("bu", movie.getBanner_url());
				moviej.put("tu", movie.getTrailer_url());
				array.put(moviej);
			}
			moviesj.put("movies", array);

			for (Movie movie : movies)
				out.println(movie.getTitle());

			//out.print(moviesj);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static ArrayList<Movie> fastSearchMoviestitle(ArrayList<String> titleTokens, Connection connection)
			throws SQLException {
		String whereConditions = " WHERE ";
		String orderByCondition = "";
		// SELECT title FROM movies WHERE MATCH (title) AGAINST ('+revenge star
		// of' IN BOOLEAN MODE);
	
		if (titleTokens.size() == 1) {
			whereConditions += "MATCH (movies.title) AGAINST ('" + titleTokens.get(0) + "*' IN BOOLEAN MODE)";
		} else {
			for (String token : titleTokens) {
				if (!token.equals("") && !token.contains(" ")) {
					if (titleTokens.indexOf(token) == titleTokens.size() - 1) {
						whereConditions += token + "*' IN BOOLEAN MODE)";
					} else if (titleTokens.indexOf(token) == 0) {
						whereConditions += "MATCH (movies.title) AGAINST ('" + token + "* ";
					} else {
						whereConditions += token + "* ";
					}
				}
			}
		}
		String sqlStatement = "SELECT distinct movies.title FROM movies "
				+ whereConditions + orderByCondition;
		Statement searchStatement = connection.createStatement();
		ResultSet resultSet = searchStatement.executeQuery(sqlStatement);
	
		ArrayList<Movie> movies = new ArrayList<Movie>();
	
		while (resultSet.next()) {
			Movie movie = new Movie();
			movie.setTitle(resultSet.getString(1));
			movies.add(movie);
		}
	
		return movies;
	}

	public static ArrayList<Movie> fastSearchMovies(ArrayList<String> titleTokens, Connection connection)
			throws SQLException {
		String whereConditions = " WHERE ";
		String orderByCondition = "";
		// SELECT title FROM movies WHERE MATCH (title) AGAINST ('+revenge star
		// of' IN BOOLEAN MODE);

		if (titleTokens.size() == 1) {
			whereConditions += "MATCH (movies.title) AGAINST ('" + titleTokens.get(0) + "*' IN BOOLEAN MODE)";
		} else {
			for (String token : titleTokens) {
				if (!token.equals("") && !token.contains(" ")) {
					if (titleTokens.indexOf(token) == titleTokens.size() - 1) {
						whereConditions += token + "*' IN BOOLEAN MODE)";
					} else if (titleTokens.indexOf(token) == 0) {
						whereConditions += "MATCH (movies.title) AGAINST ('" + token + "* ";
					} else {
						whereConditions += token + "* ";
					}
				}
			}
		}
		String sqlStatement = "SELECT movies.id, movies.title, movies.year, movies.director, movies.banner_url, movies.trailer_url FROM movies "
				+ whereConditions + orderByCondition;
		Statement searchStatement = connection.createStatement();
		
		ResultSet resultSet = searchStatement.executeQuery(sqlStatement);

		ArrayList<Movie> movies = new ArrayList<Movie>();

		while (resultSet.next()) {
			Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
			movies.add(movie);
		}

		return movies;
	}

}
