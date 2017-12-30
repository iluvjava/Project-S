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
import net.miginfocom.swing.MigLayout;

public class ScraperGui implements View{

	private JFrame frame;
	private JTextField textField;
	private JSpinner spinner;
	private JTextPane txtpnDisplay;
	private JButton btnStart;
	private JMenuItem mntmOpenFilechooser;
	private Object G_mainListener;
	private JPanel panel;
	private FileChooserDialog G_Filechooserdialogwin;

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
		frame.setBounds(100, 100, 915, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		btnStart = new JButton("Start");
		frame.getContentPane().add(btnStart, BorderLayout.SOUTH);
		
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(50);
		
		spinner = new JSpinner();
		panel.add(spinner);
		spinner.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		
		txtpnDisplay = new JTextPane();
		frame.getContentPane().add(txtpnDisplay, BorderLayout.CENTER);
		txtpnDisplay.setEditable(false);
		txtpnDisplay.setForeground(Color.GREEN);
		txtpnDisplay.setBackground(Color.DARK_GRAY);
		txtpnDisplay.setText("Display");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpenFilechooser = new JMenuItem("Open FileChooser");
		
		
		// Can through null exception.
		mntmOpenFilechooser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				FileChooserDialog temp = new FileChooserDialog();
				
				System.out.println("Adding listener to dialog:"+G_mainListener);
				temp.getFileChooser().addActionListener((ActionListener) G_mainListener);
				
				G_Filechooserdialogwin = temp;
				temp.setVisible(true);
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
	public JMenuItem getMntmOpenFilechooser() {
		return mntmOpenFilechooser;
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public View addListener(Object l) 
	{
		if(l instanceof ActionListener)
		{
			this.btnStart.addActionListener((ActionListener) l);
			this.textField.addActionListener((ActionListener) l);
		}
		
		this.G_mainListener = l;
		return this;
	}

	@Override
	public JTextPane getTextPanel() {

		return this.getTxtpnDisplay();
	}
	
	
	/**
	 * 
	 * @return
	 * The file chooser object to the model, null if it haven't been opened yet.
	 */
	public JFileChooser getFileChooser()
	{
		if(this.G_Filechooserdialogwin==null)return null;
		return this.G_Filechooserdialogwin.getFileChooser();
	}
}
