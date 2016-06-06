package servlets;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.connect;
import queries.Query;
import data_beans.Movie;

@WebServlet("/FabFlixMovieList")
public class FabFlixMovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private Connection connection;
	
	private static ArrayList<String> validSearchParams = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
	{
	    add("title");
	    add("year");
	    add("director");
	    add("first_name");
	    add("last_name");
	    add("genre");
	    add("from_search");
	    add("substring_match");
	}};

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			// Time an event in a program to nanosecond precision
			long startTime_ts = System.nanoTime();
			long startTime_tj = 0;
			long endTime_ts = 0;
			long endTime_tj = 0;
			long elapsedTime_ts = 0;
			long elapsedTime_tj = 0;
			
			connection=connect.check(connection);
			
			
			String title, year, director, first_name, last_name, genre;
			title = year = director = first_name = last_name = genre = "";
			
			Boolean sub_match = false;
			Boolean from_search = false;
			
			int page = 0;
			int limit = 5;
			String order = "titleasc";//default
			
			if (request.getParameter("lim") != null)
			{
				limit = Integer.parseInt((String) request.getParameter("lim"));
			}
			
			if (request.getParameter("page") != null)
			{
				page = Integer.parseInt((String) request.getParameter("page"));
			}
			
			if (request.getParameter("sort") != null)
			{
				order = request.getParameter("sort");
			}
			
			if (request.getParameter("title") != null) 
			{
				title = request.getParameter("title");
			}
			
			if (request.getParameter("year") != null)
			{
				year = request.getParameter("year");
			}
			
			if (request.getParameter("director") != null)
			{
				director = request.getParameter("director");
			}
			
			if (request.getParameter("first_name") != null)
			{
				first_name = request.getParameter("first_name");
			}
			
			if (request.getParameter("last_name") != null)
			{
				last_name = request.getParameter("last_name");
			}
			
			if (request.getParameter("genre") != null) 
			{
				genre = request.getParameter("genre");
			}
			
			if (request.getParameter("from_search") != null) 
			{
				from_search = true;
				
				if (request.getParameter("substring_match") != null) 
				{
					sub_match = true;
				}
			}
			
			ArrayList<Movie> movies;
			
			if (from_search) 
			{
				// Time an event in a program to nanosecond precision
				startTime_tj = System.nanoTime();
				/////////////////////////////////
				movies = Query.search("", title, year, director, genre, first_name, last_name, order, sub_match, connection);
				/////////////////////////////////
				endTime_tj = System.nanoTime();
				}
			else 
			{
				movies = Query.browse(title, genre, order, connection);
			}
			
			if (order != null && !order.isEmpty())
			{
				if (order.equals("titleasc"))
				{
					Collections.sort(movies, new Comparator<Movie>() {
						
						public int compare(Movie o1, Movie o2) 
						{
							return (o1.getTitle().compareTo(o2.getTitle()));
						}
					});
				}
				else if (order.equals("titledsc"))
				{
					Collections.sort(movies, new Comparator<Movie>() {
						
						public int compare(Movie o1, Movie o2) 
						{
							return (o1.getTitle().compareTo(o2.getTitle()));
						}
					});
					Collections.reverse(movies);
				}
				else if (order.equals("yearasc"))
				{
					Collections.sort(movies, new Comparator<Movie>() {
						
						public int compare(Movie o1, Movie o2) 
						{
							if (o1.getYear() == o2.getYear()) {
								return 0;
							}
							return o1.getYear() < o2.getYear() ? -1 : 1;
						}
					});
				}
				else if (order.equals("yeardsc"))
				{
					Collections.sort(movies, new Comparator<Movie>() {
						
						public int compare(Movie o1, Movie o2) 
						{
							if (o1.getYear() == o2.getYear()) {
								return 0;
							}
							return o1.getYear() < o2.getYear() ? 1 : -1;
						}
					});
				}
			}
			
			ArrayList<Movie> finalMovies = new ArrayList<Movie>();
			int offset = (page * limit);
			
			for (int increment = offset; increment < (offset + limit); increment++)
			{
				if (increment <= movies.size() - 1) 
				{
					finalMovies.add(movies.get(increment));
				}
			}
			
			ArrayList<String> parameterArray = new ArrayList<String>();
			
			for (String retval: request.getQueryString().split("&"))
			{
				for (String param : validSearchParams)
				{
					if (retval.startsWith(param))
					{
						parameterArray.add(retval);
					}
				}
		    }
			
			String queries = "";
			
			for (String param : parameterArray)
			{
				queries += param + "&";
			}
			
			request.setAttribute("queries", queries);
			request.setAttribute("movies", finalMovies);
			request.setAttribute("lim", limit);
			request.setAttribute("page", page);
			request.setAttribute("sort", order);
			
			request.setAttribute("maxPage", (int)Math.ceil(movies.size() / limit));
			
			request.getRequestDispatcher("FabFlixMovieList.jsp").forward(request, response);
			
			if (connection != null && !connection.isClosed())
			{
				connection.close();
			}
			
			///////////////////////////////
			endTime_ts = System.nanoTime();
			elapsedTime_tj = endTime_tj - startTime_tj; // elapsed time in nano seconds. Note: print the values in nano seconds 
			elapsedTime_ts = endTime_ts - startTime_ts; // elapsed time in nano seconds. Note: print the values in nano seconds 

			String filename = "/home/ubuntu/search_log.txt";
			File f = new File(filename);
			PrintWriter out = null;
			
			String detailLog  = "\n" + "TS: " + elapsedTime_ts + " TJ: " + elapsedTime_tj;
			if ( f.exists() && !f.isDirectory() ) {
				out = new PrintWriter(new FileOutputStream(new File(filename), true)); 
				out.append(detailLog);
				out.close();
			}
			else {
				out = new PrintWriter(filename);
				out.println(detailLog);
				out.close();
				}
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
