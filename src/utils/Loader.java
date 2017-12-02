package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import models.Genre;
import models.Movie;
import models.Rating;
import models.User;

public class Loader {
	
	//whatever I don't care
	private Map<Integer, List<Rating>> userRatings;
	private Map<Integer, Genre> movieGenres;
	
	public Loader(){
		
		movieGenres = new HashMap<Integer, Genre>();
		
		for(Genre genre : Genre.values()){
			movieGenres.put(genre.ordinal(), genre);
		}
		
		try {
			userRatings = loadRatings();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<Integer, User> loadUsers() throws IOException{
		String delims = "[|]";
		
		//Dictionary of userID -> User 
		Map<Integer, User> users = new HashMap<Integer, User>();
		Scanner in = new Scanner(new File("data/users5.dat"));
		
		while (in.hasNextLine()) {
            String userDetails = in.nextLine();
            // parse user details string
            String[] userTokens = userDetails.split(delims);

            if (userTokens.length == 7) {
            	
            	int userID = Integer.parseInt(userTokens[0]);
            	
                User temp = new User(userID, userTokens[1], userTokens[2], Integer.parseInt(userTokens[3]),
                		userTokens[4].charAt(0), userRatings.get(userID));
                users.put(userID, temp);
            } else {
                in.close();
                throw new IOException("Invalid member length: " + userTokens.length);
            }
        }
        in.close();
        return users;
	}
	
	private Map<Integer, List<Rating>> loadRatings() throws IOException{
		String delims = "[|]";
		Map<Integer, List<Rating>> ratings = new HashMap<Integer, List<Rating>>();
		Scanner in = new Scanner(new File("data/ratings5.dat"));
		
		while (in.hasNextLine()) {
            String ratingDetails = in.nextLine();
            // parse user details string
            String[] ratingTokens = ratingDetails.split(delims);

            if (ratingTokens.length == 4) {
            	int userID = Integer.parseInt(ratingTokens[0]);
            	int movieID = Integer.parseInt(ratingTokens[1]);
                Rating temp = new Rating(userID,movieID , Integer.parseInt(ratingTokens[2]));
                if(!ratings.containsKey(userID)){
                	List<Rating> tempList = new ArrayList<Rating>();
                	tempList.add(temp);
                	ratings.put(userID, tempList);
                	continue;
                }
                List<Rating> tempList = ratings.get(userID);
                boolean found = false;
                for(int i = 0; i < tempList.size(); ++i){
                	if(tempList.get(i).getMovieID() == movieID){
                		if(tempList.get(i).getTimestamp() < temp.getTimestamp())
                			tempList.set(i, temp);
                		found = true;
                		break;
                	}
                }
                if(!found) tempList.add(temp);
                ratings.put(userID, tempList);
            } else {
                in.close();
                throw new IOException("Invalid member length: " + ratingTokens.length);
            }
        }
		
        in.close();
        return ratings;
	}
	
	public Map<Integer, Movie> loadMovies() throws IOException{
		String delims = "[|]";
		
		//Dictionary of movieID -> Movie 
		Map<Integer, Movie> movies = new HashMap<Integer, Movie>();
		Scanner in = new Scanner(new File("data/items5.dat"));
		
		while (in.hasNextLine()) {
            String movieDetails = in.nextLine();
            // parse user details string
            String[] movieTokens = movieDetails.split(delims);

            if (movieTokens.length == 23) {
            	
            	int movieID = Integer.parseInt(movieTokens[0]);
            	List<Genre> genres = new ArrayList<Genre>();
            	
            	for(int i = 4; i < movieTokens.length; ++i){
            		int genreValue = Integer.parseInt(movieTokens[i]);
            		if(genreValue == 0) continue;
            		genres.add(movieGenres.get(i-4));
            	}
            	
                Movie temp = new Movie(movieID, movieTokens[1], movieTokens[2], movieTokens[3], genres);
                movies.put(movieID, temp);
            } else {
                in.close();
                throw new IOException("Invalid member length: " + movieTokens.length);
            }
        }
        in.close();
        return movies;
	}
}
