package Gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTree;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Dialog;
import java.awt.Window;
import java.awt.Window.Type;

public class ScraperGui implements View{

	private JFrame frame;
	private JTextField textField;
	private JSpinner spinner;
	private JPanel panel;
	private JTextPane txtpnDisplay;
	private JButton btnStart;
	private JMenuItem mntmOpenFilechooser;
	private Object G_mainListner;

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
		try {
			UIManager.setLookAndFeel(
				    UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
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
		frame.setAutoRequestFocus(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 774, 529);
		frame.getContentPane().add(panel);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(660, 77, 93, 37);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.setLayout(null);
		
		txtpnDisplay = new JTextPane();
		txtpnDisplay.setBounds(21, 77, 606, 371);
		panel.add(txtpnDisplay);
		txtpnDisplay.setEditable(false);
		txtpnDisplay.setForeground(Color.GREEN);
		txtpnDisplay.setBackground(Color.DARK_GRAY);
		txtpnDisplay.setText("Display");
		panel.add(btnStart);
		
		spinner = new JSpinner();
		spinner.setBounds(660, 21, 59, 36);
		spinner.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		panel.add(spinner);
		
		textField = new JTextField();
		textField.setBounds(21, 21, 606, 35);
		panel.add(textField);
		textField.setColumns(50);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpenFilechooser = new JMenuItem("Open FileChooser");
		mntmOpenFilechooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser temp = new FileChooser();
				temp.getFrame().setVisible(true);
				temp.getFileChooser().addActionListener((ActionListener) G_mainListner);
			}
		});
		mntmOpenFilechooser.setIcon(new ImageIcon(ScraperGui.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mntmOpenFilechooser.setSelectedIcon(new ImageIcon(ScraperGui.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mnFile.add(mntmOpenFilechooser);
		
		
	}
	
	
	/**
	 * Return that component that can help the user to choose a file. 
	 * @return
	 */
	
	
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
	public JFrame getFrame() {
		return frame;
	}
	public JMenuItem getMntmOpenFilechooser() {
		return mntmOpenFilechooser;
	}

	@Override
	public View addListener(Object l) 
	{
		if(l instanceof ActionListener)
		{
			this.btnStart.addActionListener((ActionListener) l);
			this.textField.addActionListener((ActionListener) l);
		}
		
		this.G_mainListner = l;
		return this;
	}

	@Override
	public JTextPane getTextPanel() {

		return this.getTxtpnDisplay();
	}
}
