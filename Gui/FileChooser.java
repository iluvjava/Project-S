package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

class FileChooser {

	private JFrame frame;
	private JFileChooser fileChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileChooser window = new FileChooser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileChooser() {
		try {
			UIManager.setLookAndFeel(
				    UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} // this code will get the native look and feel of the 
				//system. 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 850, 495);
		
		fileChooser = new JFileChooser();
		fileChooser.setDialogType(1);
		fileChooser.setFileSelectionMode(1);
		frame.getContentPane().add(fileChooser, BorderLayout.CENTER);
		

		
	}

	public JFileChooser getFileChooser()
	{
		return fileChooser;
	}
	public JFrame getFrame() {
		return frame;
	}
}
