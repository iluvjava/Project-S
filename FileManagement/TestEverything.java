package FileManagement;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEverything {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws NotADirectoryException 
	{
		testStoringObjects();
		
	}

	
	public void testStoringObjects() throws NotADirectoryException
	{
		String dir ="E:/ObjectTest/";
		int[] athing = {4,3,2,1,345,66,445,0,-12};
		ObjectCache objc = new ObjectCache(athing,new File(dir),"athing");
		
		println(objc);
		
		println("Here is where the file store: "+ objc.getStoreFile());
		
		println(objc);
		
		Object something = objc.readObject();
		
		println(something);
		
		
		
		
	}
	
	public static void println(Object o )
	{
		System.out.println(o);
	}
}
