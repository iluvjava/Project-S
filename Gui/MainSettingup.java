package Gui;

/**
 * This class will connect the lisrener to the gui
 * @author victo
 *
 */
public class MainSettingup 
{
	public static void main(String[] args)
	{
		ScraperGui view = new ScraperGui();
		Controller con = new Controller();
		view.getBtnStart().addActionListener(con);
		view.getFrame().setVisible(true);
	}

}
