package models;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable{

	//Serial for serialization
	private static final long serialVersionUID = -4037667193584389013L;
	private int movieID;
	private String title;
	private String date;
	private String url;
	private List<Genre> genres;
	
	public Movie(int movieID, String title, String date, String url, List<Genre> genres){
		if(movieID <= 0) throw new IllegalArgumentException("ID cannot be negative or zero");
		if(title == null || title.isEmpty()) throw new IllegalArgumentException("Title cannot be null or empty");
		this.movieID = movieID;
		this.title = title;
		this.date = date;
		this.url = url;
		this.genres = genres;
	}

	
	//GETTERS
	
	public int getMovieID() {
		return movieID;
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public String getUrl() {
		return url;
	}

	public List<Genre> getGenres() {
		return genres;
	}
	
	@Override
	public String toString(){
		String genreString = " ";
		for(Genre genre : genres) genreString += genre + ",";
		return movieID+"  -  "+title+"  Date: "+date+"  URL: "+url+ "  Genres: "+genreString.substring(0, genreString.length()-1);
	}
}
