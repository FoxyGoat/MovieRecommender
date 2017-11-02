package controllers;

import models.Genre;
import models.Movie;
import models.Rating;
import models.User;

import java.io.File;
import java.util.List;

public interface RecommenderAPI {
	
	/**
	 * Gets all known Users
	 * @return
	 */
	List<User> getUsers();
	
	/**
	 * Gets all known Movies
	 * @return
	 */
	List<Movie> getMovies();
	
	/**
	 * Get top ten movies
	 * @return
	 */
	List<Movie> getTopTenMovies();
	
	/**
	 * Adds a new User
	 * @param firstName : String
	 * @param lastName	: String
	 * @param age		: Integer
	 * @param gender	: char
	 * @param occupation: Occupation
	 * @return Boolean which signifies if the User was added successfully
	 */
	boolean addUser(String firstName, String lastName, int age, char gender);
	
	/**
	 * Removes a User by userID
	 * @param userID	: Integer
	 * @return Boolean which signifies if the User was removed successfully
	 */
	boolean removeUser(int userID);
	
	/**
	 * Add a new Movie
	 * @param title		: String
	 * @param year		: Integer
	 * @param url		: String
	 * @return Boolean which signifies if the Movie was added successfully
	 */
	boolean addMovie(String title, String Date, String url, List<Genre> genres);
	
	/**
	 * Add a new Rating for User on Movie
	 * @param userID	: Integer
	 * @param movieID	: Integer
	 * @param rating	: Integer
	 * @return Boolean which signifies if the Rating was added successfully
	 */
	boolean addRating(int userID, int movieID, int rating);
	
	
	/**
	 * 
	 * @param userID	: Integer
	 * @return User of which the userID was given, or null if no User with userID found
	 */
	User getUser(int userID);
	
	/**
	 * 
	 * @param movieID 	: Integer
	 * @return Movie of which movieID was given, or null if no Movie with movieID found
	 */
	Movie getMovie(int movieID);
	
	/**
	 * 
	 * @param userID 	: Integer
	 * @return Return a List of Rating from user of given ID, null if the User has no ratings or the user doesn't exist
	 */
	List<Rating> getUserRatings(int userID);
	
	/**
	 * 
	 * @param title		: String
	 * @return Return a Movie with the given title, null if the movie is not found
	 */
	Movie getMovieByTitle(String title);
	
	/**
	 * 
	 * @param year		: Integer
	 * @return Return a Movie with the given year, null if the movie is not found
	 */
	Movie getMovieByYear(String date);
	
	/**
	 * The initial loading of data containing the users, ratings and movies
	 */
	void initialLoad();
	
	/**
	 * Writes the current data to a file
	 */
	void write();
	
	/**
	 * Reads from an existing save file the data (if one exists)
	 * @param saveFile		: File 
	 */
	void load(File saveFile);
}
