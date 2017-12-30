package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JTextPane;

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
 * @author victo
 *
 */
public class GuiModel 
{
	ScraperGui G_GUI;
	
	public GuiModel(View arg)
	{
		if(arg instanceof View)this.G_GUI = (ScraperGui) arg;
	}
	
	
	/**
	 * This class will store text for display and their corresponding methods. 
	 * @author victo
	 *
	 */
	class DisplayText
	{
		private JTextPane G_JTpane;
		
		public DisplayText(JTextPane panel)
		{
			this.G_JTpane=panel;
		}
		
	}
	
	
	

}



