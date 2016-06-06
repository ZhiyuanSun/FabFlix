package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import connect.connect;
import data_beans.Movie;

/**
 * Servlet implementation class info
 */
@WebServlet("/info")
public class info extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public info() {
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
			String title = request.getParameter("title");
			PrintWriter out = response.getWriter();
			Movie movie = fastSearchMovies(title, conn);
			if (movie != null)
				toApp(movie, out);
			else
				out.println("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void toApp(Movie movie, PrintWriter out) throws IOException {
		// TODO Auto-generated method stub
		try {
			JSONObject moviej = new JSONObject();

			moviej.put("Id", movie.getId());
			moviej.put("title", movie.getTitle());
			moviej.put("year", movie.getYear());
			moviej.put("director", movie.getDirector());
			moviej.put("bu", movie.getBanner_url());
			moviej.put("tu", movie.getTrailer_url());

						
			out.print(moviej);
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

	public static Movie fastSearchMovies(String title, Connection connection) throws SQLException {

		String sqlStatement = "SELECT movies.id, movies.title, movies.year, movies.director, movies.banner_url, movies.trailer_url FROM movies where movies.title='"
				+ title + "'";
		Statement searchStatement = connection.createStatement();
		ResultSet resultSet = searchStatement.executeQuery(sqlStatement);

		if (!resultSet.next())
			return null;

		Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
				resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

		return movie;
	}

}
