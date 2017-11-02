package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class MovieTest {

	private Movie movie;
	
	@Before
	public void setup(){
		movie = new Movie(1, "TestTitle", "20-Jan-2006", "www.none.com", new ArrayList<Genre>());
	}
	
	@After
	public void tearDown(){
		movie = null;
	}
	
	@Test
	public void testConstructor() {
		//Test ID = 0
		try{
			new Movie(0, "TestTitle", "20-Jan-2006", "www.none.com", new ArrayList<Genre>());
			fail("Testing ID == 0");
		}catch(IllegalArgumentException e){}
		
		//Test ID negative
		try{
			new Movie(-1, "TestTitle", "20-Jan-2006", "www.none.com", new ArrayList<Genre>());
			fail("Testing negative ID");
		}catch(IllegalArgumentException e){}
		
		//Test Title null
		try{
			new Movie(1, null, "20-Jan-2006", "www.none.com", new ArrayList<Genre>());
			fail("Testing null Title");
		}catch(IllegalArgumentException e){}
		
		//Test Title empty
		try{
			new Movie(1, "", "20-Jan-2006", "www.none.com", new ArrayList<Genre>());
			fail("Testing empty Title");
		}catch(IllegalArgumentException e){}
	}
	
	@Test
	public void testGetters(){
		assertEquals(1, movie.getMovieID());
		assertEquals("TestTitle", movie.getTitle());
		assertEquals("www.none.com", movie.getUrl());
		assertEquals("20-Jan-2006", movie.getDate());
		assertEquals(0, movie.getGenres().size());
	}
	
	@Test
	public void testGenres(){
		//Assert there are no genres at the start
		assertEquals(0, movie.getGenres().size());
		
		List<Genre> genres = new ArrayList<Genre>();
		genres.add(Genre.ACTION);
		genres.add(Genre.ADVENTURE);
		genres.add(Genre.HORROR);
		
		movie = new Movie(1, "TestTitle", "20-Jan-2006", "www.none.com", genres);
		
		//Assert there are no genres at the start
		assertEquals(3, movie.getGenres().size());
		assertEquals(Genre.ACTION, movie.getGenres().get(0));
		assertEquals(Genre.ADVENTURE, movie.getGenres().get(1));
		assertEquals(Genre.HORROR, movie.getGenres().get(2));
	}
	
	@Test
	public void testToString(){
		assertEquals( "1  -  TestTitle  Date: 20-Jan-2006  URL: www.none.com  Genres: ", movie.toString());
	}

}
