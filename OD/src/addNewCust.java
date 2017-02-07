import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import javax.swing.SwingConstants;
import java.awt.Font;

public class addNewCust extends mainMenu{

	JFrame frmAddNewCustomer;
	private JTextField txtFirstName;
	private JTextField txtLastNam;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtZipcode;
	private JComboBox stateBox;
	private JTextField pN1;
	private JTextField pN2;
	private JTextField pN3;
	private JLabel label_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addNewCust window = new addNewCust();
					window.frmAddNewCustomer.setLocationRelativeTo(null);
					window.frmAddNewCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addNewCust() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddNewCustomer = new JFrame();
		frmAddNewCustomer.setResizable(false);
		frmAddNewCustomer.setTitle("Add New Customer");
		frmAddNewCustomer.setBounds(100, 100, 475, 315);
		frmAddNewCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddNewCustomer.getContentPane().setLayout(null);
		
		txtFirstName = new JTextField();
		txtFirstName.setText("First name");
		txtFirstName.setBounds(10, 11, 203, 32);
		frmAddNewCustomer.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		txtFirstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtFirstName.getText().trim().equals(""))
					txtFirstName.setText("First name");
			}
		});
		txtFirstName.addKeyListener(new KeyAdapter() {
			int keyPress = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				if(keyPress < 1){
					txtFirstName.selectAll();
					keyPress++;
				}
			}
		});
		
		txtLastNam = new JTextField();
		txtLastNam.setText("Last name");
		txtLastNam.setBounds(10, 54, 203, 32);
		frmAddNewCustomer.getContentPane().add(txtLastNam);
		txtLastNam.setColumns(10);
		txtLastNam.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtLastNam.getText().trim().equals("Last name"))
					txtLastNam.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtLastNam.getText().trim().equals(""))
					txtLastNam.setText("Last name");
			}
		});
		txtLastNam.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { 
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					newCustomer();
					frmAddNewCustomer.dispose();
				}
			}
		});
		
		pN1 = new JTextField(3);
		pN1.setHorizontalAlignment(SwingConstants.CENTER);
		pN1.setToolTipText("Phone Number");
		pN1.setBounds(20, 97, 36, 32);
		frmAddNewCustomer.getContentPane().add(pN1);
		pN1.setColumns(3);
		
		
		pN2 = new JTextField(3);
		pN2.setHorizontalAlignment(SwingConstants.CENTER);
		pN2.setToolTipText("Phone Number");
		pN2.setColumns(3);
		pN2.setBounds(66, 97, 36, 32);
		frmAddNewCustomer.getContentPane().add(pN2);
		
		pN3 = new JTextField(4);
		pN3.setHorizontalAlignment(SwingConstants.CENTER);
		pN3.setToolTipText("Phone Number");
		pN3.setColumns(4);
		pN3.setBounds(112, 97, 48, 32);
		frmAddNewCustomer.getContentPane().add(pN3);

		pN3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { 
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					newCustomer();
					frmAddNewCustomer.dispose();
				}
			}
		});
/**
 *  /////////////////////////////////////////////////// Phone number fields auto-tab, change code if found better solution /////////////////////////////////////////////////////////		
 */
		pN1.addKeyListener(new KeyAdapter() {
			int count1 = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				count1++;
				if(count1==3){
					pN2.requestFocus();
				}
				
			}
		});
		
		pN2.addKeyListener(new KeyAdapter() {
			int count1 = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				count1++;
				if(count1==3){
					pN3.requestFocus();
				}
				
			}
		});
		
		pN3.addKeyListener(new KeyAdapter() {
			int count1 = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				count1++;
				if(count1==4){
					txtAddress.requestFocus();
				}
				
			}
		});

/**
 *  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */
		txtAddress = new JTextField();
		txtAddress.setText("Address");
		txtAddress.setBounds(10, 140, 309, 32);
		frmAddNewCustomer.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		txtAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtAddress.getText().trim().equals("Address"))
					txtAddress.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtAddress.getText().trim().equals(""))
					txtAddress.setText("Address");
			}
		});
		
		txtCity = new JTextField();
		txtCity.setText("City");
		txtCity.setBounds(10, 183, 203, 32);
		frmAddNewCustomer.getContentPane().add(txtCity);
		txtCity.setColumns(10);
		txtCity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtCity.getText().trim().equals("City"))
					txtCity.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtCity.getText().trim().equals(""))
					txtCity.setText("City");
			}
		});
		
		txtZipcode = new JTextField();
		txtZipcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { 
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					newCustomer();
					frmAddNewCustomer.dispose();
				}
			}
		});
		txtZipcode.setText("Zipcode");
		txtZipcode.setBounds(75, 226, 138, 32);
		frmAddNewCustomer.getContentPane().add(txtZipcode);
		txtZipcode.setColumns(10);
		txtZipcode.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtZipcode.getText().trim().equals("Zipcode"))
					txtZipcode.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtZipcode.getText().trim().equals(""))
					txtZipcode.setText("Zipcode");
			}
		});
		
		stateBox = new JComboBox();
		stateBox.setModel(new DefaultComboBoxModel(new String[] {"", "AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY"}));
		stateBox.setBounds(10, 226, 55, 32);
		frmAddNewCustomer.getContentPane().add(stateBox);
		
		JButton btnNewButton = new JButton("Add Customer");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				newCustomer();
				frmAddNewCustomer.dispose();
			}
		});
		btnNewButton.setBounds(296, 226, 138, 32);
		frmAddNewCustomer.getContentPane().add(btnNewButton);

		
		JLabel label = new JLabel("(");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(10, 97, 10, 30);
		frmAddNewCustomer.getContentPane().add(label);
		
		JLabel label_1 = new JLabel(")");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(56, 96, 10, 32);
		frmAddNewCustomer.getContentPane().add(label_1);
		
		label_2 = new JLabel("-");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(101, 97, 10, 32);
		frmAddNewCustomer.getContentPane().add(label_2);
		
		
	}
	
	void newCustomer(){
		try{
			
			String pNumber = ("("+pN1.getText()+")"+pN2.getText()+"-"+pN3.getText());
			
			String query = "insert into customers (`First Name`, `Last Name`, `Phone number`, Address, City, Zipcode, State) values (?,?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, txtFirstName.getText());
			pst.setString(2, txtLastNam.getText());
			pst.setString(3, pNumber);
			pst.setString(4, txtAddress.getText());
			pst.setString(5, txtCity.getText());
			pst.setString(6, txtZipcode.getText());
			pst.setString(7, (String) stateBox.getSelectedItem());
			
			pst.execute();
			
			JOptionPane.showMessageDialog(null, "Customer Added");
			pst.close();
		}
		catch(Exception e1){
				e1.printStackTrace();
		}
	}
}
