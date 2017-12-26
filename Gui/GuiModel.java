package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * This class will be the model behind the gui 
 * and control the program. 
 * @author victo
 *
 */
public class GuiModel 
{
	ScraperGui G_GUI;
	
	public GuiModel(ScraperGui arg)
	{
		this.G_GUI = arg;
	}
	
	
	
	
	

}

/**
 * This class will be connected to the view.
 * It will be linked to the gui model...
 * @author victo
 *
 */
class Controller implements EventListener, ActionListener
{
	
	GuiModel G_model;
	
	public Controller(GuiModel arg)
	{
		this.G_model = arg;
	}
	@Override
	
	
	/**
	 * When the start botton is pressed, this method will execute everything
	 * <ul>
	 * <li>1. Exame the input url, and number in the spinner
	 * <li>2. Call factory method and response to the screen accordingly.
	 * <li>3. Direct output to another package if possible
	 * </ul>
	 * 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		println("This is the action command:");
		println(e.getActionCommand());
		
		println("Trying to get file chooser info");
		String s = this.G_model.G_GUI.getFileChooser().getSelectedFile().toString();
		println(s);
		
		
		
	}
	
	
	public void println(Object o)
	{
		this.G_model.G_GUI.getTxtpnDisplay().setText(o.toString());
	}
	
	
	/*
	 * This is a class that will format and store buffere text that is going to he text
	 * panel. 
	 */
	static class TextAreaBuffer
	{
		
		
	}
	
}