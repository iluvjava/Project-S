package Gui;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

import Scraping.Scrapable;
import Scraping.Scraper;
import WebPage.DeviantArt;

/**
 * This class will be the model behind the gui 
 * and control the program. 
 * This is the brain of this program, it should deal with many things. 
 * 
 * <ul>
 * <li>Print lines of text to the output window.
 * <li>Handle the event and produce correct output to the 
 * window when user interact with the view.
 * </ul>
 * 
 * <p>
 * The model contains all the neccessary information to create a SINGLE scrapable 
 * object and control the object through the methods is have. 
 * @author victo
 *
 */
public class GuiModel 
{
	ScraperGui G_GUI;
	public static volatile DisplayText G_displayedText; // the text panel essentially. 
	
	
	/********************things for data part**********************/
	File G_dir;
	String G_initiallink;
	private Scrapable G_scr;
	private Scraper G_scraper;
	int G_target=0;
	
	public GuiModel(View arg)
	{
		if(arg instanceof View)this.G_GUI = (ScraperGui) arg;
		
		G_displayedText = new DisplayText(G_GUI.getTextArea());
	}
	
	
	public static void print(Object o)
	{
		if(GuiModel.G_displayedText!=null)G_displayedText.print(o);
	}
	public static void println(Object o)
	{
		if(GuiModel.G_displayedText!=null)GuiModel.G_displayedText.println(o);
	}
	
	
	/**
	 * Tested.
	 * This class will store text for display and their corresponding methods. 
	 * @author victo
	 *
	 */
	public class DisplayText 
	{
		private TextArea G_JTpane;
		
		
		public DisplayText(TextArea panel)
		{
			this.G_JTpane=panel;
		}
		
		
		
		public synchronized void print(Object o)
		{
			this.G_JTpane.append(o.toString());
			this.G_JTpane.setCaretPosition(this.G_JTpane.getCaretPosition()+1);
		}
		
		public synchronized void println(Object o)
		{
			print(o.toString()+"\n");
		}
		
		public void setText(String s)
		{
			this.G_JTpane.setText(s);
		}
		
		
		
//		private void clear()
//		{
//			this.G_JTpane.setText(null);
//			
//		}

		
	}
	
	
	/**
	 * <p>
	 * this method will tries to create a scrapable object in the field.
	 * @return
	 * A boolean to indecate whether the process is successful. 
	 */
	public boolean createScrapableFromLink()
	{
		
		if(this.G_dir == null)
		{
			GuiModel.G_displayedText.println("File path is not specified.");return false;
		}
		this.G_target = (int) this.G_GUI.getSpinner().getValue();
		this.G_initiallink = this.G_GUI.getTextField().getText();
		println("Setting target: "+this.G_target);
		
		//create scraper
		if(DeviantArt.isInDadomain(this.G_initiallink))
		{
			//
			println("Setting the URL: "+ this.G_initiallink);
			println("This is a Deviant art link....");
			
			try 
			{
				this.G_scr = new DeviantArt(this.G_initiallink);
				this.G_scraper = new Scraper(this.G_scr,this.G_dir,this.G_target);
				return true;
				
			} catch (IOException e) 
			{
				e.printStackTrace();
				return false;
			}
			
			
			
			
		}
		
		
		println("Cannot Recognize url.");return false;
		
		
		
	}


	/**
	 * Import file to the field. 
	 */
	public void setDirectory() 
	{
		File f = this.G_GUI.getFileChooser().getSelectedFile();
		this.G_dir = f; 
		GuiModel.println("Setting Directory: "+ f);
		//this.G_GUI.getFileChooser().setCurrentDirectory(f);
	}


	public void removeDirectory() 
	{
		this.G_dir= null;
		println("Removing Directery to null.");
	}


	/**
	 * Under Testing.
	 */
	public void startExecute() 
	{
		this.G_GUI.getBtnStart().setEnabled(false);
		if(this.createScrapableFromLink())
		{
			//Start the scraping. 
			this.G_scraper.execute();
			
		}
		
		
		this.G_GUI.getBtnStart().setEnabled(true);
	}
	
	
	public void closeFileChooser()
	{
		FileChooserDialog temp=this.G_GUI.getFileChooserDialog();
		if(temp!=null)temp.setVisible(false);
		
	}
	

}



