package Scraping;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;

import WebPage.DeviantArt;
import WebPage.WebPage;

/**
 * Let's together define some methods for the scrapable behaviours.
 * 
 * <ul>
 * <li>There is a string collection that contains all the visited url
 * it can be used as a reference to how many webs has been visisted
 * but it should not be absolute reference. 
 * </ul>
 * @author victo
 *
 */
public interface Scrapable 
{
	
	// an optional thing to help you. 
	// prevents from visting the same web pages repeatedly.
	Collection<String> G_alreadyVistedURL =new HashSet<String>();
	
	
	
	/**
	 * Preparing for getting the similiar web sites. 
	 * 1.
	 * 2. collect next web page, it can be more, 
	 * 	or it can be one; or null
	 * 
	 * <ul> 
	 * <li>The scope is: analysing the websites,
	 * <li>This method is for preparation
	 * <li>You can add the urls to the public field of this interface. So
	 * you implemented class will know the string representation of the websites that 
	 * has visited already. 
	 * <li>
	 * </ul>
	 * 
	 * @throws IOException
	 */
	 Scrapable createSynapse();
	
	
	/**
	 * Create and return the next collection of web pages.
	 * @return
	 * null if there is non. 
	 * @throws IOException
	 */
	public Collection<Scrapable> getNextWebPages()throws IOException;
	
	
	/**
	 * Return that input stream that represent the content. 
	 * @throws IOException
	 */
	public InputStream doTheScraping()throws IOException;

	/**
	 * This is a method will return the url of the object
	 * it will be used for creating a file. 
	 * @return
	 */
	public String getSourceContentUrl();
	
	/**
	 * 
	 * @return
	 * The postfix that should goes with the file;
	 */
	
	
	
	/**
	 * This is a method that take in a scrapable object and try to 
	 * get a name for the file using the url return by the object. 
	 * 
	 * it will check the instance of the input parameter and 
	 * decide what post fix of the file should receive. 
	 * @param arg
	 * @return
	 */
	public static String getFilenameFromScrapable(Scrapable scr)
	{
		String arg = scr.getSourceContentUrl();
		
		int temp = arg.lastIndexOf('/');
		
		if(scr instanceof DeviantArt)
		{
			
		}
		
		return arg.substring(temp+1, arg.length());
	}
	
	
	/**
	 * 
	 * Unit Tested. 
	 * This method take in a valid file and 
	 * download the content from the srapable to the file.
	 * This what this method does:
	 * 1. take in the file object; 
	 * 2. get the directory and 
	 * @param
	 * The file should represent a directory
	 * @return
	 * a boolean to represent whether the operation is successful.
	 * @throws
	 * Illegalargumnet exception is the give file is not a directory. 
	 * or if the file doesn't exist at all; 
	 */
	public static boolean createFileFromScrapable(File arg, Scrapable scr)
	{
		if(!arg.isDirectory())throw new IllegalArgumentException();
		if(!arg.exists())throw new IllegalArgumentException();
		
		
		
		arg = new File(arg.getAbsolutePath()+"\\"+Scrapable.getFilenameFromScrapable(scr));
		if(arg.exists())
		{
			println("File:"+ arg.getAbsolutePath()+" already existed. ");
			return true;
		}
		
		println("We are trying to create a file for a scrapable under dir:");
		println(arg.getAbsolutePath());
		
		try 
		{
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arg));
			BufferedInputStream is = new BufferedInputStream(scr.doTheScraping());
			int temp;
			int loopcount=0;
			while((temp = is.read())!=-1)
			{
				
				bos.write(temp);
				loopcount++;
			}
			println("Buffered InputStream byte count: "+ loopcount);
			is.close();
			bos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public static void println(Object o )
	{
		System.out.println(o);
	}
	
	
	
	
}
