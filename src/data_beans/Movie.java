package data_beans;

import java.util.ArrayList;

public class Movie 
{
	private int id;
	private int year;
	private String title;
	private String director;
	private String banner_url;
	private String trailer_url;
	
	private ArrayList<Genre> genres;
	private ArrayList<Star> stars;
	
	public Movie(int id, String title, int year, String director, String banner_url, String trailer_url) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.genres = new ArrayList<Genre>();
		this.stars = new ArrayList<Star>();
		
		setBanner_url(banner_url);
		setTrailer_url(trailer_url);
	}
	
	public Movie(int id, String title, int year, String director,
			String banner_url, String trailer_url, ArrayList<Genre> genres,
			ArrayList<Star> stars) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.genres = genres;
		this.stars = stars;
		
		setBanner_url(banner_url);
		setTrailer_url(trailer_url);
	}

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public void addGenre(Genre genre)
	{
		genres.add(genre);
	}
	
	public void addStar(Star star)
	{
		stars.add(star);
	}
		
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getBanner_url() {
		return banner_url;
	}
	
	public void setBanner_url(String banner_url) {
		if (!banner_url.toLowerCase().matches("^\\w+://.*")) {
			banner_url = "http://" + banner_url;
		}
		this.banner_url = banner_url;
	}
	
	public String getTrailer_url() {
		return trailer_url;
	}
	
	public void setTrailer_url(String trailer_url) {
		if (!trailer_url.toLowerCase().matches("^\\w+://.*")) {
			trailer_url = "http://" + trailer_url;
		}
		this.trailer_url = trailer_url;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

	public ArrayList<Star> getStars() {
		return stars;
	}

	public void setStars(ArrayList<Star> stars) {
		this.stars = stars;
	}
}
