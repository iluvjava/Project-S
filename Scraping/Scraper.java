package Scraping;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import FileManagement.NotADirectoryException;
import FileManagement.ObjectCache;
import Gui.GuiModel;



/**<b>This class operates on a scrapable</b>
 * <ul>
 * <li>This class will take in a scrapable and treat it like a recursive data structure.
 * <li>It will create the object using recursion and add all the created object in 
 * the multithreaded queue. 
 * <li>When all the objects are added to the queue, this class will get inputream from 
 * each of the scrapble and create a file based on all the collected object. 
 * <li>after all the downloading to the directory is completed, it will read
 * the colletion of all the visited webs and store them in the directory,
 * next time it will loadthem up to the scrapable interface. 
 * 
 *</ul>
 * 
 * @author Rainbow Dash Is the BEST PONY! 
 *
 */
public class Scraper
{
	protected ConcurrentLinkedQueue<Scrapable> G_waitingfordonwload;
	
	
	private int target;
	
	protected Scrapable G_rootcrapable;
	
	public final String G_directory;
	
	private File G_directory_file;

	/**
	 * 
	 * @param scr
	 * @param sysdirectory
	 * A valid File representing the directory for downloading the files. 
	 * @param target
	 * A integer that is natural number and smaller than 500;
	 */
	public Scraper(Scrapable scr, File sysdirectory, int target)
	{
		if(target<1||target>=500||!sysdirectory.exists()||!sysdirectory.isDirectory())
		{
			throw new AssertionError("Scrapar class invariant dissatified. ");
		}
		this.G_directory = sysdirectory.getAbsolutePath();
		this.G_directory_file = sysdirectory;
		this.target = target;
		this.G_rootcrapable = scr;
		this.G_waitingfordonwload = new ConcurrentLinkedQueue<Scrapable>();
		this.setupArchive();
	}
	
	
	/**
	 * <b>Unit Tested, 75% safe</b>
	 * <p>
	 * This method will do the actual scraping of the scrapable in the class.
	 * This should be the class that can handle the download error
	 * 
	 * <p>
	 * It will recurse through the structure recursively.
	 * it will get the list of scrapables from the root one and add them to the 
	 * queue. 
	 * 
	 * <b>We will start with a horizontal recursive mode for the best range of scraping. </b>
	 * 
	 * <P>after the goal is reached, this method will also tries to save the collection
	 * of bisited websites in the local disk.
	 */
	public void execute()
	{
		println("Executing...");
		
		this.G_waitingfordonwload.add(this.G_rootcrapable); // remeber to add the root.....
		
		this.execute_Helper(this.G_rootcrapable);
		while(!this.G_waitingfordonwload.isEmpty())
		{
			Scrapable s = this.G_waitingfordonwload.remove();
			Scrapable.createFileFromScrapable(this.G_directory_file, s);
		}
		
		this.StoreTheArchive();
		
	}
	
	
	/**Unite Tested 
	 * <p>
	 * This method will recurse through the structure, base case is if the returned 
	 * collection of scrapable is null or the goal has reached.
	 * @param node
	 */
	private void execute_Helper(Scrapable node)
	{
		if(this.G_waitingfordonwload.size()>this.target)return;// base case; 
		Collection<Scrapable> thestuff=null;
		try 
		{
			thestuff = node.getNextWebPages();
			this.G_waitingfordonwload.addAll(thestuff);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		if(thestuff==null)return;// don't explode. also, base case....
		for(Scrapable s :thestuff)
		{
			s.createSynapse();
		}
		for(Scrapable s : thestuff)
		{
			this.execute_Helper(s);
		}
	}
	
	public String toString()
	{
		String s = new String();
		s+="\n\n\n-------------------"+this.getClass()+"-----------------\n";
		s+="Target: "+ this.target+"\n";
		s+=this.G_waitingfordonwload.isEmpty()?"The queue is empty\n"
				:
			"The size of the queu: "+this.G_waitingfordonwload.size()+'\n';
		s+="This is the system directory that download to: "+
			this.G_directory+'\n';
		return s; 
	}
	
	public static void println(Object o)
	{
		GuiModel.println(o);
		System.out.println(o);
	}
	
	
	
	/**Unite Tested
	 * <p>
	 * This method will tries to use the directory in the field to look for the 
	 * archived webs site file in the specific directory
	 * <p>
	 * If this method successfully located the method, it will tries to 
	 * read the file and return it. 
	 * @return
	 * 
	 * null if the file is not found.
	 */
	private Collection<String> getArchive()
	{
		String directory = this.G_directory;
		File  directoryfile = new File(directory);
		Collection<String> result = null;
		
		try
		{
			ObjectCache<Collection<String>> objc 
			=
			new ObjectCache<Collection<String>>(null,directoryfile,"archive"); // This is the name of the file that 
			// is string on the hard disk. 
			
			if(objc.isThere())result = objc.readObject();
		} 
		catch (NotADirectoryException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * United Tested
	 * <p>
	 * This is non static method that will try to set up the collection 
	 * of all the visited web sites in the scrapable interface. 
	 * <p>
	 * If this method works successfully, it should set up the vairable in the 
	 * scapable interface if not, it won't modifies anything.
	 *
	 */
	private void setupArchive()
	{
		
		println("Trying to see if we can set up the archive....");
		Collection<String> temp= this.getArchive();
		if(temp!=null)
		{
			println("There is an archive and we read it: ");
			Scrapable.G_alreadyVistedURL.addAll(temp);
			println(temp);
		}
		else
		{
			println("We didn't find any archive......");
		}
		
	}
	
	
	/**
	 * Unit Tested
	 * <p>
	 * This s a non static method that will store the collection of visted websites in the scapable 
	 * interface onto the hard disk. 
	 */
	private void StoreTheArchive()
	{
		Collection<String> stuff = Scrapable.G_alreadyVistedURL;
		try {
			ObjectCache<Collection<String>> objc 
			=
			new ObjectCache<Collection<String>>(stuff,this.G_directory_file,"archive");
			
			boolean result = objc.writeObject();
			
			println("Ths scraper is storing the the visited web sites onto the hard disk; ");
			if(result)println("Here is the result: sucessful");else println("Unsucessful...");
		} catch (NotADirectoryException e) {
			// do nothing. 
			e.printStackTrace();
		}
	}
	
	
	
	
	
}