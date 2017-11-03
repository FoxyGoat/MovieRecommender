package utils;

import static org.junit.Assert.*;


import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.Genre;
import models.Movie;
import models.User;

public class LoaderTest {


		private Loader load;
		
		/**
		 * Runs before every test
		 */
		@Before
		public void setup()
		{
			load = new Loader();
		}
		
		/**
		 * Runs after every test
		 */
		@After
		public void tearDown()
		{
			load = null;
		}
		
		/**
		 * Testing loading in the users
		 * @throws Exception
		 */
		@Test
		public void testLoadUsers() throws Exception {
			Map<Integer,User> users = load.loadUsers();
			assertEquals("Leonard",users.get(1).getFirstName());
			assertEquals(1,users.get(1).getUserID());
			assertEquals(1,users.get(1).getUserID());
			assertEquals("Leonard",users.get(1).getFirstName());
			
			assertNotEquals(users.get(1),users.get(2));
			assertNotEquals(users.get(1),users.get(3));
		}
		
		/**
		 * Testing loading in the items
		 * @throws Exception
		 */
		@Test
		public void testLoadMovies() throws Exception {
			Map<Integer,Movie> movies = load.loadMovies();
			//There are 3 invalid items in the items.dat file
			assertEquals(10,movies.size(),0.01);
			assertEquals("Toy Story (1995)",movies.get(1).getTitle());
			assertEquals(1,movies.get(1).getMovieID());
			assertEquals(10,movies.get(10).getMovieID());
			assertEquals("Dead Man Walking (1995)",movies.get(9).getTitle());
			
			assertNotEquals(movies.get(1),movies.get(2));
			assertNotEquals(movies.get(1),movies.get(10));
			
			//Testing Genres of Items
			assertEquals(3,movies.get(1).getGenres().size(),0.01);
			assertEquals(Genre.ANIMATION,movies.get(1).getGenres().get(0));
			assertEquals(Genre.CHILDRENS,movies.get(1).getGenres().get(1));
			assertEquals(Genre.COMEDY,movies.get(1).getGenres().get(2));
		}


	}
	


