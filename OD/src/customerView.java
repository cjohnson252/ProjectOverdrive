import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class customerView {

	JFrame frmCustomerView;
	JLabel lblName;
	JLabel lblPhone;
	JLabel lblAddress;
	JLabel lblCityZipState;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerView window = new customerView();
					window.frmCustomerView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public customerView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCustomerView = new JFrame();
		frmCustomerView.setTitle("Customer View");
		frmCustomerView.setBounds(100, 100, 692, 445);
		frmCustomerView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmCustomerView.getContentPane().setLayout(null);
		frmCustomerView.setResizable(false);
		
		lblName = new JLabel("NameGoesHere");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblName.setBounds(25, 29, 264, 60);
		frmCustomerView.getContentPane().add(lblName);
		
		lblPhone = new JLabel("PhoneNumber");
		lblPhone.setBounds(27, 91, 177, 21);
		frmCustomerView.getContentPane().add(lblPhone);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(25, 145, 210, 21);
		frmCustomerView.getContentPane().add(lblAddress);
		
		lblCityZipState = new JLabel("City, Zip, State");
		lblCityZipState.setBounds(25, 168, 210, 21);
		frmCustomerView.getContentPane().add(lblCityZipState);
	}
	void initializeInventoryView(){
		connection = sqliteConnection.dbConnector();
		try{
			String query = "select `First Name`";
			PreparedStatement pst = connection.prepareStatement(query);
			lblName.setText(query);
			
			ResultSet res = pst.executeQuery();
			
			
			
			
			pst.close();
			res.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
