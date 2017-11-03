package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RatingTest {
	
	private Rating rating;
	
	/**
	 * Runs before every test
	 */
	
	@Before
	public void setup()
	{
		rating = new Rating(1,2,3);
	}
	
	/**
	 * Runs after every test
	 */
	@After
	public void tearDown()
	{
		rating = null;
	}
	
	//Testing exception handling
	@Test 
	public void testContructorUserIDFail()
	{
		try{
			new Rating(-1, 1,1);
			fail();
		}
		catch(Exception e){
		
		}
	
	}
	
	@Test 
	public void testContructorItemIDFail()
	{
		try{
			new Rating(1, -1,1);
			fail();
		}
		catch(Exception e){
		
		}
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testContructorRatingHighFail()
	{
		new Rating(1, 1,6);
		fail();
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testContructorRatingLowFail()
	{
		new Rating(1, 1,-6);
		fail();
	}
	
	/**
	 * Constructor testing
	 */
	@SuppressWarnings("unused")
	@Test 
	public void testContructor()
	{
		Rating rating1 = new Rating(5, 10,5);
		Rating rating2 = new Rating(1, 1,-5);
		Rating rating3 = new Rating(100, 100,0);
		Rating rating4 = new Rating(1,1,1);
		Rating rating5 = new Rating(5, 10,5);
		Rating rating6 = rating2;
		
		
		assertSame(rating6,rating2);
	}
	
	/**
	 * Getters testing
	 */
	@Test
	public void testGetters()
	{
		
		assertEquals(1,rating.getUserID(),0.01);
		assertEquals(2,rating.getMovieID(),0.01);
		assertEquals(3,rating.getRating(),0.01);
	}
	
	/**
	 * ToString testing
	 */
	@Test
	public void testToString()
	{
		Rating rating = new Rating(1,2,3);
		assertEquals("Rating\nUser ID: 1\nMovie ID: 2\nRating: 3\n",rating.toString());
	}
	
	


}
