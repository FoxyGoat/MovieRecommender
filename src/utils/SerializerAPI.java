package utils;

public interface SerializerAPI {

	/**
	 * Pushes the given object onto a stack data structure of objects
	 * @param o
	 */
	void push(Object o);
	
	/**
	 * Pops and returns an object off the stack
	 * @return
	 */
	Object pop();
	
	/**
	 * Writes (Serializes) the stack to file
	 * @throws Exception
	 */
	void write() throws Exception;
	
	/**
	 * Reads (De-Serializes) the stack from file
	 * @throws Exception
	 */
	void read() throws Exception;
	
}
