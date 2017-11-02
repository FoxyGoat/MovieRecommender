package models;

import java.io.Serializable;

public class Rating implements Comparable<Rating>, Serializable{
	
	//Serial for serialization
	private static final long serialVersionUID = 5007105902416314927L;
	private int userID;
	private int movieID;
	private int rating;
	private long timestamp; 
	
	public Rating(int userID, int movieID, int rating, long timestamp){
		this.userID = userID;
		this.movieID = movieID;
		this.rating = rating;
		this.timestamp = timestamp;
	}
	
	public Rating(int userID, int movieID, int rating){
		if(rating > 5 || rating < -5) throw new IllegalArgumentException();
		this.userID = userID;
		this.movieID = movieID;
		this.rating = rating;
		this.timestamp = System.currentTimeMillis() / 1000l;
	}

	//GETTERS + SETTERS
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public long getTimestamp(){
		return timestamp;
	}
	
	
	@Override
	public String toString(){
		return userID+"  -  "+movieID+"  =>  "+rating;
	}

	@Override
	public int compareTo(Rating other) {
		return (int) (this.timestamp - other.timestamp);
	}
	
}
