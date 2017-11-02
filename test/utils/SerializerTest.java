package utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class SerializerTest {

	private Loader loader;
	
	@Before
	public void setup(){
		loader = new Loader();
	}
	
	@After
	public void tearDown(){
		loader = null;
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
