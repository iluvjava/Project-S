package Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * This class will be connected to the view.
 * It will be linked to the gui model...
 * @author victo
 *
 */
class Controller implements  ActionListener
{
	
	GuiModel G_model;
	
	public Controller(GuiModel arg)
	{
		this.G_model = arg;
	}
	@Override
	
	
	/**
	 * When the start botton is pressed,this method will reports action to the model
	 * 
	 * <ul>
	 * <li>1. Direct output to another package if possible
	 * <li>2. tries to let the model to deal with it. 
	 * </ul>
	 * 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(e.getActionCommand());
		
		
	}
	
	
	
	
	
}
