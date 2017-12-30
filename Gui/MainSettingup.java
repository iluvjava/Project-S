package Gui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class will connect the lisrener to the gui
 * @author victo
 *
 */
public class MainSettingup 
{
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		
		View v = new ScraperGui();
		
		v.getFrame().setVisible(true);
		
		GuiModel m = new GuiModel(v);
		
		Controller con = new Controller(m);
		
		v.addListener(con);
	}

}
