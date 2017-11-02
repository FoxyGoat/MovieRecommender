package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class XMLSerializer implements SerializerAPI {

	private Stack<Object> objectStack;
	private File file;
	
	public XMLSerializer(File file){
		this.file = file;
		objectStack = new Stack<Object>();
	}
	
	@Override
	public void push(Object o) {
		objectStack.push(o);
	}

	@Override
	public Object pop() {
		return objectStack.pop();
	}

	@Override
	public void write() throws Exception {
		ObjectOutputStream os = null;

	    try
	    {
	      os =  new ObjectOutputStream(new GZIPOutputStream(new BufferedOutputStream ( new FileOutputStream(file) ) ) );
	      os.writeObject(objectStack);
	    }
	    finally
	    {
	      if (os != null)
	      {
	        os.close();
	      }
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read() throws Exception {
		ObjectInputStream is = null;

	    try
	    {
	      is =  new ObjectInputStream(new GZIPInputStream(new BufferedInputStream ( new FileInputStream(file) ) ) );
	      objectStack = (Stack<Object>) is.readObject();
	    }
	    finally
	    {
	      if (is != null)
	      {
	        is.close();
	      }
	    }
	}

}
