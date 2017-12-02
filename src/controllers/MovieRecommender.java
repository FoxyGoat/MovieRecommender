package controllers;

import java.util.List;

import models.Genre;
import models.Movie;
import models.Rating;
import models.User;
import utils.Loader;
import utils.SerializerAPI;
import utils.XMLSerializer;

import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MovieRecommender implements RecommenderAPI{

	private Map<Integer, User> users;
	private Map<Integer, Movie> movies;
	private Loader loader;
	private SerializerAPI serializer;
	
	
	public MovieRecommender(File file){
		users = new HashMap<Integer, User>();
		movies = new HashMap<Integer, Movie>();
		serializer = new XMLSerializer(file);
	}
	
	@Override
	public List<User> getUsers(){
		return new ArrayList<User>(users.values());
	}
	
	@Override
	public List<Movie> getMovies(){
		return new ArrayList<Movie>(movies.values());
	}
	
	@Override
	public List<Movie> getTopTenMovies(){
		List<Movie> topTen = new ArrayList<Movie>();
		
		//Movie to their rating sum map
		Map<Movie, Integer> movieRatings = new HashMap<Movie, Integer>();
		
		//Cycle through all the ratings (from all the users)
		for(User user : users.values()){
			for(Rating rating : user.getRatings()){
				
				Movie movie = movies.get((rating.getMovieID()));
				
				//If movie is not in movieRatings, add it and give it the
				//starting value of this rating
				if(movieRatings.get(movie) == null){
					movieRatings.put(movie, rating.getRating());
					continue;
				}
				
				//Otherwise add the current rating value to the movie (movieRatings)
				int current = movieRatings.get(movie);
				current += rating.getRating();
				movieRatings.put(movie, current);
			}
		}
		
		//Loop 10 times (for top ten duh)
		for(int i = 0; i < 10; ++i){
			
			Movie highestMovie = null;
			int highestRating = Integer.MIN_VALUE;
			
			//For all the movies in movieRatings, if it's not already in the topTen
			//get it's rating and compare to the highestRating, if it's greater, make highestMovie
			//equal to this movie (as a potential candidate)
			for(Movie movie : movieRatings.keySet()){
				if(topTen.contains(movie)) continue;
				if(movieRatings.get(movie) > highestRating) highestMovie = movie;
			}
			
			//Add the current highest movie to the topTen
			topTen.add(highestMovie);
		}
		
		return topTen;
	}
	
	@Override
	public boolean addUser(String firstName, String lastName, int age, char gender) {
		int availableID = 1;
		for(; availableID < users.size(); ++availableID){
			if(users.get(availableID) == null) break;
		}
		User temp = new User(++availableID, firstName, lastName, age, gender, new ArrayList<Rating>());
		users.put(availableID, temp);
		return true;
	}

	@Override
	public boolean removeUser(int userID) {
		return users.remove(userID) != null;
	}

	@Override
	public boolean addMovie(String title, String date, String url, List<Genre> genres) {
		int availableID = 1;
		for(; availableID < movies.size(); ++availableID){
			if(movies.get(availableID) == null) break;
		}
		Movie temp = new Movie(++availableID, title, date, url, genres);
		movies.put(temp.getMovieID(), temp);
		return true;
	}

	@Override
	public boolean addRating(int userID, int movieID, int rating) {
		User targetUser = users.get(userID);
		if(targetUser == null) return false;
		if(movies.get(movieID) == null) return false;
		
		boolean found = false;
		for(int i = 0; i < targetUser.getRatings().size(); ++i){
			if(targetUser.getRatings().get(i).getMovieID() == movieID){
				found = true;
				targetUser.setRating(i, movieID, rating);
				break;
			}
		}
		
		if(!found){
			targetUser.addRating(movieID, rating);
		}
		
		return true;
	}
	
	@Override
	public User getUser(int userID){
		return users.get(userID);
	}

	@Override
	public Movie getMovie(int movieID) {
		return movies.get(movieID);
	}

	@Override
	public List<Rating> getUserRatings(int userID) {
		User temp = users.get(userID);
		if(temp == null) return null;
		return temp.getRatings();
	}

	@Override
	public Movie getMovieByTitle(String title) {
		for(Movie movie : movies.values()){
			if(movie.getTitle().toUpperCase().replaceAll("\\s","").equals(title.toUpperCase().replaceAll("\\s",""))){
				return movie;
			}
		}
		return null;
	}

	@Override
	public Movie getMovieByYear(String date) {
		for(Movie movie : movies.values()){
			if(movie.getDate().toUpperCase().replaceAll("\\s","").equals(date.toUpperCase().replaceAll("\\s",""))){
				return movie;
			}
		}
		return null;
	}
	
	@Override
	public List<Movie> searchByMovieTitle(String movieTitleString){
		List<Movie> foundMovies = new ArrayList<Movie>();
		
		for(int i : movies.keySet()){
			if(movies.get(i).getTitle().toLowerCase().contains(movieTitleString.toLowerCase())){
				foundMovies.add(movies.get(i));
			}
		}
		
		return foundMovies;
	}

	@Override
	public void initialLoad() {
		loader = new Loader();
		try{
			users = loader.loadUsers();
			movies = loader.loadMovies();
		}catch(Exception e){
			// TODO add some legit exception catching
			System.err.println("Reading files error");
		}
		
	}

	@Override
	public void write() {
		serializer.push(users);
		serializer.push(movies);
		try {
			serializer.write();
		} catch (Exception e) {
			System.err.println("ERROR! Could not use the Serializer to write!\n\n");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load(File saveFile) {
		try {
			serializer.read();
		} catch (Exception e) {
			System.err.println("ERROR! Could not use the Serializer to read!\n\n");
			e.printStackTrace();
			return;
		}
		
		movies = (Map<Integer, Movie>) serializer.pop();
		users = (Map<Integer, User>) serializer.pop();
	}

}
