package models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;

		User testUser;
		
		/**
		 * Runs before every test
		 */
		@Before
		public void setup()
		{
			testUser = new User(5,"John","Cena",19,'M', new ArrayList<Rating>());
		}
		
		/**
		 * Runs after every test
		 */
		@After
		public void tearDown()
		{
			testUser = null;
		}
		
		//Testing exception handling
		@Test (expected = IllegalArgumentException.class)
		public void testContructorFailID() {
			new User(-1,"Test1","Test2",19,'M',new ArrayList<Rating>());
			fail();
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void testContructorFailFirstName1() {
			new User(1,"","Test2",19,'M',new ArrayList<Rating>());
		}
		
		@Test (expected = NullPointerException.class)
		public void testContructorFailFirstName2() {
			new User(1,null,"Test2",19,'M',new ArrayList<Rating>());
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void testContructorFailLastName1() {
			new User(1,"Test1","",19,'M',new ArrayList<Rating>());
		}
		
		@Test (expected = NullPointerException.class)
		public void testContructorFailLastName2() {
			new User(1,"Test1",null,19,'M',new ArrayList<Rating>());
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void testContructorFailAge1() {
			new User(1,"Test1","Test2",0,'M',new ArrayList<Rating>());
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void testContructorFailAge2() {
			new User(1,"Test1","Test2",150,'M',new ArrayList<Rating>());
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void testContructorFailGender() {
			new User(1,"Test1","Test2",19,'m',new ArrayList<Rating>());
		}
		
		/**
		 * Getters testing
		 */
		@Test
		public void testGetters()
		{
			assertEquals(5,testUser.getUserID());
			assertEquals("John",testUser.getFirstName());
			assertEquals("Cena",testUser.getLastName());
			assertEquals(19,testUser.getAge());
			assertEquals('M',testUser.getGender());
			
		}
		
		/**
		 * ToString testing
		 */
		@Test
		public void testToString()
		{
			assertEquals("5  -  John Cena   Age: 19   Gender: M",testUser.toString());
		}

	


}
