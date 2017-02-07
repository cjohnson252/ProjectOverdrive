/**
 * This is the login window class of Overdrive application.
 * In this class the validity of login credentials is checked
 * against SQLite database and based on validity access is granted.
 * 
 * For the purpose of test official login credentials are
 * username: admin
 * password: 1234
 * 
 * Use these credentials to test the login 
 * 
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Panel;
import javax.swing.JProgressBar;
import java.awt.Label;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;

public class LoginWindow extends mainMenu{

	static JFrame frmOverdrive;
	private JPasswordField pwdPassword;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow loginWindow = new LoginWindow();
					LoginWindow.frmOverdrive.setLocationRelativeTo(null);
					loginWindow.frmOverdrive.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Check credentials against employees table of SQLite DB
	 */
	@SuppressWarnings("deprecation")
	public void checkValid(){
		try{
			String query ="select * from employees where username=? and password=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, txtUsername.getText());
			pst.setString(2, pwdPassword.getText());
			
			ResultSet res = pst.executeQuery();
			int count = 0;
			while(res.next()){
				count++;
			}
			if(count == 1){
				initProgram();
			}
			else if(count > 1){
				JOptionPane.showMessageDialog(null, "Duplicate username and password");
			}
			else{
				JOptionPane.showMessageDialog(null, "Username and/or password is not correct, please try again");
			}
			res.close();
			pst.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	/**
	 * initialize mainMenu method
	 */
	private void initProgram() {
		mainMenu mM = new mainMenu();
		mM.frmOverdrive.setVisible(true);
		mM.frmOverdrive.setLocationRelativeTo(null);
		frmOverdrive.dispose();
	}

	Connection connection=null;
	/**
	 * Initialize LoginWindow and the DB connection
	 */
	public LoginWindow() {
		initialize();
		connection=sqliteConnection.dbConnector();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * Login frame parameters
		 */
		frmOverdrive = new JFrame();
		frmOverdrive.setResizable(false);
		frmOverdrive.setTitle("Overdrive");
		frmOverdrive.setBounds(100, 100, 595, 443);
		frmOverdrive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOverdrive.getContentPane().setLayout(null);
		
		/**
		 * Password text box parameters
		 */
		pwdPassword = new JPasswordField();
		pwdPassword.setToolTipText("Enter password here");
		pwdPassword.setHorizontalAlignment(SwingConstants.CENTER);
		pwdPassword.setText("1234");
		pwdPassword.setBounds(332, 198, 174, 31);
		frmOverdrive.getContentPane().add(pwdPassword);
		
		/**
		 * Password box auto refiller
		 */
		pwdPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(pwdPassword.getText().trim().equals("1234"))
					pwdPassword.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(pwdPassword.getText().trim().equals(""))
					pwdPassword.setText("1234");
			}
		});

		/**
		 * Bind Enter to login button in password field
		 */
		pwdPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					checkValid();
				}
			}
		});
		
		/**
		 * Username text box parameters
		 */
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setToolTipText("Enter username here");
		txtUsername.setText("Username");
		txtUsername.setBounds(332, 156, 174, 31);
		frmOverdrive.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		/**
		 * Bind Enter to login button in username field
		 */
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					checkValid();
				}
			}
		});
		/**
		 * Username text box auto refiller
		 */
		txtUsername.addKeyListener(new KeyAdapter() {
			int keyPress = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				if(keyPress < 1){
					txtUsername.selectAll();
					keyPress++;
				}
			}
		});
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsername.getText().trim().equals(""))
			           txtUsername.setText("Username");
			}
		});
		/**
		 * Login button parameters 
		 */
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnLogin.setBounds(332, 270, 174, 31);
		frmOverdrive.getContentPane().add(btnLogin);
		
		/**
		 * Mouse press trigger for the login button
		 */
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkValid();
			}
		});
		
		/**
		 * Label Overdrive just in case the window name Overdrive was not enough
		 * for you to understand you've just launched the Overdrive app
		 */
		JLabel lblOverdrive = new JLabel("Overdrive");
		lblOverdrive.setFont(new Font("Corbel", Font.PLAIN, 40));
		lblOverdrive.setHorizontalAlignment(SwingConstants.CENTER);
		lblOverdrive.setBounds(10, 23, 574, 83);
		frmOverdrive.getContentPane().add(lblOverdrive);
		
		/**
		 * Not sure about this icon but I think it works
		 */
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\F\\Documents\\OVERDRIVE\\bigicon.png"));
		lblNewLabel.setBounds(52, 117, 200, 200);
		frmOverdrive.getContentPane().add(lblNewLabel);
		
		/**
		 * Menu bar and its parameters
		 */
		JMenuBar menuBar = new JMenuBar();
		frmOverdrive.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		/**
		 * Shut down the app upon pressing Exit
		 */
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frmOverdrive.dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnHelp);
		
		/**
		 * Trigger for the About window in case of About event action
		 */
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnHelp.add(mntmAbout);
		mntmAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				About about = new About();
				about.frmAboutOverdrive.setLocationRelativeTo(null);
				about.frmAboutOverdrive.setVisible(true);
			}
		});
	}
}
