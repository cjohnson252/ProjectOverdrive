import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class addNewItem{

	JFrame frmAddNewPart;
	private JTextField txtPartNumber;
	private JTextField txtPartDescription;
	private JTextField txtVendor;
	private JTextField txtStoreLocation;
	private JTextField txtInStock;
	private JTextField txtUnitCost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addNewItem window = new addNewItem();
					window.frmAddNewPart.setLocationRelativeTo(null);
					window.frmAddNewPart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addNewItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddNewPart = new JFrame();
		frmAddNewPart.setResizable(false);
		frmAddNewPart.setTitle("Add New Part");
		frmAddNewPart.setBounds(100, 100, 457, 322);
		frmAddNewPart.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmAddNewPart.getContentPane().setLayout(null);
		
		txtPartNumber = new JTextField();
		txtPartNumber.addKeyListener(new KeyAdapter() {
			int keyPress = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				if(keyPress < 1){
					txtPartNumber.selectAll();
					keyPress++;
				}
			}
		});
		
		txtPartNumber.setText("Part Number");
		txtPartNumber.setBounds(10, 59, 360, 29);
		frmAddNewPart.getContentPane().add(txtPartNumber);
		txtPartNumber.setColumns(10);
		
		txtPartNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPartNumber.getText().trim().equals(""))
					txtPartNumber.setText("Part Number");
			}
		});
		
		
		txtPartDescription = new JTextField();
		txtPartDescription.setText("Part Description");
		txtPartDescription.setBounds(10, 99, 360, 29);
		frmAddNewPart.getContentPane().add(txtPartDescription);
		txtPartDescription.setColumns(10);
		
		txtPartDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtPartDescription.getText().trim().equals("Part Description"))
					txtPartDescription.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPartDescription.getText().trim().equals(""))
					txtPartDescription.setText("Part Description");
			}
		});
		
		
		txtVendor = new JTextField();
		txtVendor.setText("Vendor");
		txtVendor.setBounds(10, 139, 175, 29);
		frmAddNewPart.getContentPane().add(txtVendor);
		txtVendor.setColumns(10);
		
		txtVendor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtVendor.getText().trim().equals("Vendor"))
					txtVendor.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtVendor.getText().trim().equals(""))
					txtVendor.setText("Vendor");
			}
		});
		
		
		txtStoreLocation = new JTextField();
		txtStoreLocation.setText("Store Location (Ex. Inside)");
		txtStoreLocation.setBounds(195, 139, 175, 29);
		frmAddNewPart.getContentPane().add(txtStoreLocation);
		txtStoreLocation.setColumns(10);
		
		txtStoreLocation.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtStoreLocation.getText().trim().equals("Store Location (Ex. Inside)"))
					txtStoreLocation.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtStoreLocation.getText().trim().equals(""))
					txtStoreLocation.setText("Store Location (Ex. Inside)");
			}
		});
		
		
		txtInStock = new JTextField();
		txtInStock.setText("Quantity (Ex. 2.00)");
		txtInStock.setBounds(10, 179, 175, 29);
		frmAddNewPart.getContentPane().add(txtInStock);
		txtInStock.setColumns(10);
		
		txtInStock.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtInStock.getText().trim().equals("Quantity (Ex. 2.00)"))
					txtInStock.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtInStock.getText().trim().equals(""))
					txtInStock.setText("Quantity (Ex. 2.00)");
			}
		});
		
		
		txtUnitCost = new JTextField();
		txtUnitCost.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					newPart();
				}
			}
		});
		txtUnitCost.setText("Unit Cost (Ex. 56.99)");
		txtUnitCost.setBounds(195, 179, 175, 29);
		frmAddNewPart.getContentPane().add(txtUnitCost);
		txtUnitCost.setColumns(10);
		
		txtUnitCost.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtUnitCost.getText().trim().equals("Unit Cost (Ex. 56.99)"))
					txtUnitCost.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUnitCost.getText().trim().equals(""))
					txtUnitCost.setText("Unit Cost (Ex. 56.99)");
			}
		});
		
		
		JButton btnNewButton = new JButton("Add Part");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				newPart();
			}
		});
		btnNewButton.setBounds(266, 247, 175, 29);
		frmAddNewPart.getContentPane().add(btnNewButton);
	}
	
	Connection connection = sqliteConnection.dbConnector();
	void newPart(){
		try{
			String query = "insert into inventory (`Part Number`, `Part Description`, Vendor, `Store Location`, `In Stock`, `Unit Cost`) values (?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, txtPartNumber.getText());
			pst.setString(2, txtPartDescription.getText());
			pst.setString(3, txtVendor.getText());
			pst.setString(4, txtStoreLocation.getText());
			pst.setString(5, txtInStock.getText());
			pst.setString(6, txtUnitCost.getText());
			
			pst.execute();
			
			JOptionPane.showMessageDialog(null, "Part Added");
			pst.close();			
			frmAddNewPart.dispose();
		}
		catch(Exception e1){
				e1.printStackTrace();
		}
	}
}
