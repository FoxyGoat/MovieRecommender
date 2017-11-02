package controllers;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import models.User;
import utils.SerializerAPI;
import utils.XMLSerializer;
import models.Genre;
import models.Movie;
import models.Rating;

import org.junit.Before;
import org.junit.After;

public class RecommenderTest {

	private RecommenderAPI recommender;
	
	/**
	 * Runs before every test
	 */
	@Before
	public void setup()
	{
		recommender =  new MovieRecommender(null);
		recommender.initialLoad();
	}
	
	/**
	 * Runs after every test
	 */
	@After
	public void tearDown()
	{
		recommender = null;
	}
	
	//Testing getters//
	@Test
	public void testGetUsers() {
		assertEquals(5,recommender.getUsers().size());
	}

	@Test
	public void testGetMovies() {
		assertEquals(10,recommender.getMovies().size());
	}
	
	@Test
	public void testGetUser() {
		User testUser = recommender.getUser(1);
		assertEquals(testUser,recommender.getUser(1));
	}

	@Test
	public void testGetMovie() {
		List<Genre> genres = new ArrayList<Genre>();
		genres.add(Genre.ANIMATION);
		genres.add(Genre.CHILDRENS);
		genres.add(Genre.COMEDY);
		Movie testMovie = recommender.getMovie(1);
		assertEquals(testMovie,recommender.getMovieByTitle("Toy Story (1995)"));
		assertEquals(testMovie,recommender.getMovieByYear("01-Jan-1995"));
		assertEquals(testMovie,recommender.getMovie(1));
	}
	
	/**
	 * Testing for user and item existence
	 */
	@Test
	public void testExists()
	{
		User testUser = recommender.getUser(1);
		Movie testMovie = recommender.getMovie(1);
		
		assertEquals(true,recommender.getUsers().contains(testUser));
		assertEquals(true,recommender.getMovies().contains(testMovie));
	}
	
	/**
	 * Testing adding a user
	 * @throws Exception
	 */
	@Test
	public void testAddUser() throws Exception
	{
		assertEquals(5,recommender.getUsers().size());
		recommender.addUser("TestUser", "TestUserLast", 19, 'M');
		assertEquals(6,recommender.getUsers().size());
	}
	
	/**
	 * Testing removing a user
	 * @throws Exception
	 */
	@Test
	public void testRemoveUser() throws Exception
	{
		assertEquals(5,recommender.getUsers().size());
		recommender.addUser("TestUser", "TestUserLast", 19, 'M');
		assertEquals(6,recommender.getUsers().size());
		recommender.removeUser(6);
		assertEquals(5,recommender.getUsers().size());
	}
	
	/**
	 * Testing adding a movie
	 * @throws Exception
	 */
	@Test
	public void testAddMovie() throws Exception
	{
		assertEquals(10,recommender.getMovies().size());
		List<Genre> genres = new ArrayList<Genre>();
		genres.add(Genre.ACTION);
		recommender.addMovie("Test","2016","www.test.com",new ArrayList<Genre>());
		recommender.addMovie("Test2","2016","www.test.com",genres);
		assertEquals(12,recommender.getMovies().size());
	}
	
	/**
	 * Testing adding ratings 
	 * @throws Exception
	 */
	@Test
	public void testRating() throws Exception
	{
		recommender.addUser("TestUser", "TestUserLast", 19, 'M');
		recommender.addRating(6, 1, 4);
		assertEquals(1,recommender.getUser(6).getRatings().size());
		assertEquals(4,recommender.getUser(6).getRatings().get(0).getRating());
		
		recommender.addRating(6, 1, 5);
		assertEquals(1,recommender.getUser(6).getRatings().size());
		assertEquals(5,recommender.getUser(6).getRatings().get(0).getRating());
		
		recommender.addRating(6, 1, -5);
		assertEquals(1,recommender.getUser(6).getRatings().size());
		assertEquals(-5,recommender.getUser(6).getRatings().get(0).getRating());
		
		try
		{
			recommender.addRating(6, 1, 6);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			
		}
		try
		{
			recommender.addRating(6, 1, -6);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			
		}
	}
	
	/**
	 * Testing getting top ten movies
	 */
	@Test
	public void testGetTopTenMovies()
	{
		assertEquals(10,recommender.getTopTenMovies().size());
	}
	
	/**
	 * Testing the write and read methods
	 * @throws Exception
	 */
	@Test
	public void testWriteRead() throws Exception
	{
		File testFile = new File("test.xml");
		RecommenderAPI mr = new MovieRecommender(testFile);
		mr.initialLoad();
		mr.addMovie("Test1", "2016", "www.none", new ArrayList<Genre>());
		mr.addUser("FirstNameTest", "LastNameTest", 19, 'M');
		mr.addRating(6, 11, 5);

		assertEquals(6, mr.getUsers().size());
		assertEquals(11,mr.getMovies().size());
		assertEquals("FirstNameTest", mr.getUser(6).getFirstName());
		assertEquals("Test1",mr.getMovie(11).getTitle());
		assertEquals(1, mr.getUser(6).getRatings().size());
		assertEquals(5, mr.getUser(6).getRatings().get(0).getRating());
		
		mr.write();
		
		
		RecommenderAPI mr2 = new MovieRecommender(testFile);
		mr2.load(testFile);
		
		assertEquals(6, mr2.getUsers().size());
		assertEquals(11,mr2.getMovies().size());
		assertEquals("FirstNameTest", mr2.getUser(6).getFirstName());
		assertEquals("Test1",mr2.getMovie(11).getTitle());
		assertEquals(1, mr2.getUser(6).getRatings().size());
		assertEquals(5, mr2.getUser(6).getRatings().get(0).getRating());
		
		if(testFile.exists())
			testFile.delete();
	}

}
