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
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("This is the action command:");
		System.out.println(e.getActionCommand());
		
	}
	
}