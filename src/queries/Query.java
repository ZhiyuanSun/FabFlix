package queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import data_beans.Item;
import data_beans.Cart;
import data_beans.Customer;
import data_beans.Genre;
import data_beans.Movie;
import data_beans.Star;

public class Query {
	public static ArrayList<Movie> search(String movieId, String movieTitle, String movieYear, String movieDirector,
			String movieGenre, String starFirstName, String starLastName, String order, boolean sub_match,
			Connection connection) throws SQLException {
		String defaultSQL = " WHERE 1=1";
		if (movieId != null && !movieId.isEmpty()) {
			defaultSQL += " AND movies.id=\"" + movieId + "\"";
		}

		if (movieTitle != null && !movieTitle.isEmpty()) {
			if (sub_match) {
				defaultSQL += " AND movies.title LIKE \"%" + movieTitle + "%\"";
			} else {
				defaultSQL += " AND movies.title LIKE \"" + movieTitle + "\"";
			}
		}

		if (movieYear != null && !movieYear.isEmpty()) {
			defaultSQL += " AND movies.year LIKE \"" + movieYear + "\"";
		}

		if (movieDirector != null && !movieDirector.isEmpty()) {
			if (!sub_match) {
				defaultSQL += " AND movies.director = \"" + movieDirector + "\"";
			} else {
				defaultSQL += " AND movies.director LIKE \"%" + movieDirector + "%\"";
			}
		}

		if (movieGenre != null && !movieGenre.isEmpty()) {
			if (!sub_match) {
				defaultSQL += " AND genres.name = \"" + movieGenre + "\"";
			} else {
				defaultSQL += " AND genres.name LIKE \"%" + movieGenre + "%\"";
			}
		}

		if (starFirstName != null && !starFirstName.isEmpty()) {
			if (!sub_match) {
				defaultSQL += " AND stars.first_name = \"" + starFirstName + "\"";
			} else {
				defaultSQL += " AND stars.first_name LIKE \"%" + starFirstName + "%\"";
			}
		}

		if (starLastName != null && !starLastName.isEmpty()) {
			if (!sub_match) {
				defaultSQL += " AND stars.last_name = \"" + starLastName + "\"";
			} else {
				defaultSQL += " AND stars.last_name LIKE \"%" + starLastName + "%\"";
			}
		}

		return getMovie(defaultSQL, connection);
	}

	public static ArrayList<Movie> browse(String movieTitle, String movieGenre, String order, Connection connection)
			throws SQLException {
		String whereConditions = " WHERE 1=1";

		if (movieTitle != null && !movieTitle.isEmpty()) {
			whereConditions += " AND movies.title LIKE \"" + movieTitle + "%\"";
		}

		if (movieGenre != null && !movieGenre.isEmpty()) {
			whereConditions += " AND genres.name LIKE \"%" + movieGenre + "%\"";
		}

		return getMovie(whereConditions, connection);
	}

	public static ArrayList<Movie> getMovie(String whereConditions, Connection connection) throws SQLException {
		
		String sqlStatement = "SELECT stars.id, stars.first_name, stars.last_name, stars.dob, stars.photo_url, movies.id, movies.title, movies.year, movies.director, movies.banner_url, movies.trailer_url, "
				+ "genres.id, genres.name FROM movies, stars_in_movies, stars, genres_in_movies, genres"
				+ whereConditions
				+ "AND stars_in_movies.movie_id = movies.id AND stars_in_movies.star_id = stars.id AND genres_in_movies.movie_id = movies.id AND genres.id = genres_in_movies.genre_id";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
//		ResultSet resultSet = searchStatement.executeQuery(sqlStatement);
		ResultSet resultSet = preparedStatement.executeQuery();

		connection.commit();
		HashMap<Integer, Movie> movieMap = new HashMap<Integer, Movie>();

		while (resultSet.next()) {
			Star star = new Star(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getDate(4), resultSet.getString(5));
			Genre genre = new Genre(resultSet.getInt(12), resultSet.getString(13));

			if (movieMap.containsKey(resultSet.getInt(6))) {
				Movie movie = movieMap.get(resultSet.getInt(6));
				boolean addStar = true;
				boolean addGenre = true;

				for (Star existingStar : movie.getStars()) {
					if (existingStar.getId() == star.getId()) {
						addStar = false;
					}
				}

				if (addStar) {
					movie.addStar(star);
				}

				for (Genre existingGenre : movie.getGenres()) {
					if (existingGenre.getId() == genre.getId()) {
						addGenre = false;
					}
				}

				if (addGenre) {
					movie.addGenre(genre);
				}

				movieMap.put(movie.getId(), movie);
			} else {
				ArrayList<Genre> genres = new ArrayList<Genre>();
				genres.add(genre);

				ArrayList<Star> stars = new ArrayList<Star>();
				stars.add(star);

				Movie movie = new Movie(resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8),
						resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), genres, stars);
				movieMap.put(movie.getId(), movie);
			}
		}

		ArrayList<Movie> movies = new ArrayList<Movie>(movieMap.values());

		return movies;
	}

	public static Star getStar(String starId, Connection connection) throws SQLException {
		String sqlStatement = "SELECT movies.id, movies.title, movies.year, stars.id, stars.first_name, stars.last_name, stars.dob, stars.photo_url FROM stars"
				+ " INNER JOIN stars_in_movies ON stars_in_movies.star_id=stars.id"
				+ " INNER JOIN movies ON stars_in_movies.movie_id=movies.id" + " WHERE stars.id = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
		preparedStatement.setString(1, starId);

		ResultSet resultSet = preparedStatement.executeQuery();
		connection.commit();
		resultSet.next();

		Star star = new Star(resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7),
				resultSet.getString(8));
		star.addMovie(new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), "", "", ""));

		while (resultSet.next()) {
			star.addMovie(new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), "", "", ""));
		}

		return star;
	}

	public static boolean insertSales(Cart cart, Customer customer, Connection connection) {
		for (Item item : cart.getCartItems()) {
			try {
				String sql = "INSERT INTO sales(customer_id, movie_id, sale_date) VALUES(?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				java.util.Date date = new java.util.Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String today = dateFormat.format(date).toString();

				preparedStatement.setInt(1, customer.getId());
				preparedStatement.setInt(2, item.getMovieId());
				preparedStatement.setDate(3, Date.valueOf(today));

				for (int i = 0; i < item.getQuantity(); i++) {
					int resultSet = preparedStatement.executeUpdate();
					connection.commit();
					if (resultSet != 1) {
						return false;
					}
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return true;
	}

	public static Customer login_auth(String email, String password, Connection connection) throws SQLException {
		String sql = "SELECT id, first_name, last_name, cc_id, address, email, password FROM customers WHERE email = ? AND password = ?";

		PreparedStatement verifyStatement = connection.prepareStatement(sql);
		verifyStatement.setString(1, email);
		verifyStatement.setString(2, password);

		ResultSet result = verifyStatement.executeQuery();
		connection.commit();
		Customer currentCustomer = null;

		if (result.next()) {
			currentCustomer = new Customer(result.getInt(1), result.getString(2), result.getString(3),
					result.getString(4), result.getString(5), result.getString(6), result.getString(7));
		}

		return currentCustomer;
	}

	public static boolean elogin_auth(String email, String password, Connection connection) throws SQLException {
		String sql = "SELECT count(*) FROM employees WHERE email = ? AND password = ?";

		PreparedStatement verifyStatement = connection.prepareStatement(sql);
		verifyStatement.setString(1, email);
		verifyStatement.setString(2, password);

		ResultSet result = verifyStatement.executeQuery();
		connection.commit();
		result.next();

		if (result.getInt(1) == 0)
			return false;
		else
			return true;
	}

	public static int addStar(String firstName, String lastName, String dob, String photo_url, Connection connection)
			throws SQLException {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO stars (first_name, last_name, dob,photo_url) VALUES (?,?,?,?)";

		PreparedStatement stmt = connection.prepareStatement(sql);
		connection.commit();
		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		stmt.setDate(3, Date.valueOf(dob));
		stmt.setString(4, photo_url);
		int i =stmt.executeUpdate();
		connection.commit();
		return i;

	}

	public static String addMovie(String title, int year, String director, String firstName, String lastName,
			String genre, Connection connection) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "CALL add_movie(?,?,?,?,?,?)";

		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, title);
		stmt.setInt(2, year);
		stmt.setString(3, director);
		stmt.setString(4, firstName);
		stmt.setString(5, lastName);
		stmt.setString(6, genre);
		ResultSet rs = stmt.executeQuery();
		connection.commit();
		rs.next();

		return rs.getString(1);

	}

	public static ArrayList<Movie> fastSearchMovies(String[] keywords, Connection connection) throws SQLException {
		String whereConditions;
		String orderByCondition = "";
		// SELECT title FROM movies WHERE MATCH (title) AGAINST ('+revenge star
		// of' IN BOOLEAN MODE);

		if (keywords.length == 1) {
			whereConditions = " WHERE ";
			whereConditions += "MATCH (movies.title) AGAINST ('" + keywords[0] + "*' IN BOOLEAN MODE)";
		} else {
			whereConditions = " WHERE ";
			for (int i = 0; i < keywords.length; i++) {
				String token = keywords[i];
				if (!token.equals("") && !token.contains(" ")) {
					if (i == 0) {
						whereConditions += "MATCH (movies.title) AGAINST ('" + token + " ";
					} else if (i == keywords.length - 1) {
						whereConditions += token + "*' IN BOOLEAN MODE)";
					} else {
						whereConditions += token + " ";
					}
				}
			}
		}
		String sqlStatement = "SELECT movies.id, movies.title, movies.year, movies.director, movies.banner_url, movies.trailer_url FROM movies "
				+ whereConditions + orderByCondition;
		Statement searchStatement = connection.createStatement();
		ResultSet resultSet = searchStatement.executeQuery(sqlStatement);
		connection.commit();
		ArrayList<Movie> movies = new ArrayList<Movie>();

		while (resultSet.next()) {
			Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
			movies.add(movie);
		}

		return movies;
	}

}
