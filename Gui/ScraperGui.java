package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTree;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;

public class ScraperGui {

	private JFrame frame;
	private JTextField textField;
	private JSpinner spinner;
	private JPanel panel;
	private JFileChooser fileChooser;
	private JTextPane txtpnDisplay;
	private JButton btnStart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScraperGui window = new ScraperGui();
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
	public ScraperGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnStart = new JButton("Start");
		frame.getContentPane().add(btnStart, BorderLayout.SOUTH);
		
		txtpnDisplay = new JTextPane();
		txtpnDisplay.setEditable(false);
		txtpnDisplay.setForeground(Color.GREEN);
		txtpnDisplay.setBackground(Color.DARK_GRAY);
		txtpnDisplay.setText("Display");
		frame.getContentPane().add(txtpnDisplay, BorderLayout.CENTER);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(50);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		panel.add(spinner);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File Chooser");
		menuBar.add(mnNewMenu);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(1);
		fileChooser.setDialogTitle("Choose a path");
		mnNewMenu.add(fileChooser);
	}
	
	
	/**
	 * Return that component that can help the user to choose a file. 
	 * @return
	 */
	public JFileChooser getFileChooser() 
	{
		return fileChooser;
	}
	
	/**
	 * Return the numeric selection component of the window
	 * @return
	 */
	public JSpinner getSpinner() {
		return spinner;
	}
	
	/**
	 * Return input text field of the component
	 * @return
	 */
	public JTextField getTextField() {
		return textField;
	}
	
	/**
	 * 
	 * @return
	 * The output text field of the window
	 */
	public JTextPane getTxtpnDisplay() {
		return txtpnDisplay;
	}
	
	/**
	 * 
	 * @return
	 * The start button of the window. 
	 */
	public JButton getBtnStart() {
		return btnStart;
	}
}
