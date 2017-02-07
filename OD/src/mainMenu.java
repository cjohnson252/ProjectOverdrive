/**
 * This is the main window class of Overdrive application
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class mainMenu {

	JFrame frmOverdrive;
	private JTextField firstName;
	private JTextField lastName;
	public JTextField phoneNumber;
	protected JTable customer_table;
	protected JTable inventory_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu window = new mainMenu();
					window.frmOverdrive.setLocationRelativeTo(null);
					window.frmOverdrive.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	Connection connection = null;
	
	private void initialize() {
		frmOverdrive = new JFrame();
		frmOverdrive.setResizable(false);
		frmOverdrive.setTitle("Overdrive");
		frmOverdrive.setBounds(100, 100, 1356, 928);
		frmOverdrive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOverdrive.getContentPane().setLayout(null);
		
		JTabbedPane mainWindow_panel = new JTabbedPane(JTabbedPane.TOP);
		mainWindow_panel.setBorder(null);
		mainWindow_panel.setBounds(10, 72, 1330, 795);
		frmOverdrive.getContentPane().add(mainWindow_panel);
		
		JPanel customer_panel = new JPanel();
		mainWindow_panel.addTab("Customers", null, customer_panel, "Customer Database");
		customer_panel.setLayout(null);
		
		firstName = new JTextField();
		firstName.setText("First name");
		firstName.setHorizontalAlignment(SwingConstants.LEFT);
		firstName.setColumns(10);
		firstName.setBounds(956, 325, 167, 28);
		customer_panel.add(firstName);
		//focus listener for first name text Field
		firstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(firstName.getText().trim().equals("First name"))
					firstName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(firstName.getText().trim().equals(""))
					firstName.setText("First name");
			}
		});
		
		lastName = new JTextField();
		lastName.setText("Last name");
		lastName.setColumns(10);
		lastName.setBounds(1133, 325, 167, 28);
		customer_panel.add(lastName);
		//focus listener for last name text Field
		lastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(lastName.getText().trim().equals("Last name"))
					lastName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(lastName.getText().trim().equals(""))
					lastName.setText("Last name");
			}
		});
		
		phoneNumber = new JTextField();
		phoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					customer_table.requestFocus();
				}
			}
		});
		phoneNumber.setText("Phone number");
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(956, 364, 167, 28);
		customer_panel.add(phoneNumber);
		//focus listener for phone number text Field
		phoneNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(phoneNumber.getText().trim().equals("Phone number"))
					phoneNumber.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(phoneNumber.getText().trim().equals(""))
					phoneNumber.setText("Phone number");
			}
		});
		
		JButton newCustomer = new JButton("Add New Customer");
		newCustomer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				initializeCustomerView();
			}
		});
		newCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				addNewCust nc = new addNewCust();
				nc.frmAddNewCustomer.setLocationRelativeTo(null);
				nc.frmAddNewCustomer.setVisible(true);
			}
		});
		
		newCustomer.setBounds(1133, 11, 167, 28);
		customer_panel.add(newCustomer);
		
		JButton searchCustomer = new JButton("Search Customer");
		searchCustomer.setBounds(1133, 364, 167, 28);
		customer_panel.add(searchCustomer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 927, 817);
		customer_panel.add(scrollPane);
		
		customer_table = new JTable();
		customer_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customer_table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					delCustomer();
				}
			}
		});
		customer_table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(customer_table);
		customer_table.getTableHeader().setReorderingAllowed(false);
		
		customer_table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2) {
		            customerView cv = new customerView();
		            cv.frmCustomerView.setLocationRelativeTo(null);
		            cv.frmCustomerView.setVisible(true);
		        }
		    }
		});
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// NEED TO WORK ON GETTING ID OUT OF THE QUERY AND USING IT FOR THE CUSTOMER VIEW
		
		JButton btnRefresh = new JButton("Refresh List");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				initializeCustomerView();
			}
		});
		btnRefresh.setBounds(956, 11, 167, 28);
		customer_panel.add(btnRefresh);
		
		
		
		JButton btnNewButton = new JButton("Delete Customer");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				delCustomer();
			}
		});
		
		btnNewButton.setBounds(1133, 50, 167, 28);
		customer_panel.add(btnNewButton);
		
		initializeCustomerView();
		
		JPanel workOrder_panel = new JPanel();
		mainWindow_panel.addTab("Work Orders", null, workOrder_panel, "All Work Orders");
		workOrder_panel.setLayout(null);
		
		JTabbedPane order_panel = new JTabbedPane(JTabbedPane.TOP);
		order_panel.setBounds(0, 0, 1325, 828);
		workOrder_panel.add(order_panel);
		
		JPanel current_orders = new JPanel();
		order_panel.addTab("Current Orders", null, current_orders, "All current work orders");
		
		JPanel completed_orders = new JPanel();
		order_panel.addTab("Completed Orders", null, completed_orders, "All completed work orders");
		
		JPanel inventory_panel = new JPanel();
		mainWindow_panel.addTab("Inventory", null, inventory_panel, "Store Inventory");
		inventory_panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 11, 1325, 648);
		inventory_panel.add(scrollPane_1);
		
		//Initialize inventory table
		inventory_table = new JTable();
		inventory_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(inventory_table);
		inventory_table.getTableHeader().setReorderingAllowed(false);
		inventory_table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					delItem();
				}
			}
		});
		
		
		initializeInventoryView();
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initializeInventoryView();
			}
		});
		btnRefresh_1.setBounds(10, 700, 128, 34);
		inventory_panel.add(btnRefresh_1);
		
		JButton btnDelete = new JButton("Delete Item");
		btnDelete.setBounds(148, 700, 128, 34);
		inventory_panel.add(btnDelete);
		btnDelete.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent arg0){
				delItem();
			}
		});
		
		JButton btnReorder = new JButton("Reorder");
		btnReorder.setBounds(1049, 700, 128, 34);
		inventory_panel.add(btnReorder);
		
		JButton btnAddNewItem = new JButton("Add New Item");
		btnAddNewItem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				initializeInventoryView();
			}
		});
		btnAddNewItem.setBounds(1187, 700, 128, 34);
		inventory_panel.add(btnAddNewItem);
		btnAddNewItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				addNewItem ni = new addNewItem();
				ni.frmAddNewPart.setLocationRelativeTo(null);
				ni.frmAddNewPart.setVisible(true);
			}
		});
		
		JPanel manage_panel = new JPanel();
		mainWindow_panel.addTab("Manage", null, manage_panel, null);
		mainWindow_panel.setEnabledAt(3, false);
		manage_panel.setLayout(null);
		
		JTabbedPane manage_pane = new JTabbedPane(JTabbedPane.TOP);
		manage_pane.setBounds(0, 0, 1325, 767);
		manage_panel.add(manage_pane);
		
		JPanel panel_2 = new JPanel();
		manage_pane.addTab("Customers", null, panel_2, null);
		
		JPanel panel_1 = new JPanel();
		manage_pane.addTab("Work Orders", null, panel_1, null);
		
		JPanel panel_3 = new JPanel();
		manage_pane.addTab("Inventory", null, panel_3, null);
		
		JPanel panel = new JPanel();
		manage_pane.addTab("Employees", null, panel, null);
		
		JMenuBar menuBar = new JMenuBar();
		frmOverdrive.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnNew.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnFile.add(mnNew);
		
		JMenuItem mntmCustomer = new JMenuItem("Customer");
		mntmCustomer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				addNewCust nc = new addNewCust();
				nc.frmAddNewCustomer.setLocationRelativeTo(null);
				nc.frmAddNewCustomer.setVisible(true);
			}
		});
		mnNew.add(mntmCustomer);
		
		JMenuItem mntmInventoryItem = new JMenuItem("Inventory Item");
		mntmInventoryItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmInventoryItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				addNewItem ni = new addNewItem();
				ni.frmAddNewPart.setLocationRelativeTo(null);
				ni.frmAddNewPart.setVisible(true);
			}
		});
		mnNew.add(mntmInventoryItem);
		
		JMenu mnImport = new JMenu("Import");
		mnImport.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnFile.add(mnImport);
		
		JMenuItem mntmCustomersDatabaseI = new JMenuItem("Customers Database");
		mntmCustomersDatabaseI.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnImport.add(mntmCustomersDatabaseI);
		
		JMenuItem mntmInventoryDatabaseI = new JMenuItem("Inventory Database");
		mntmInventoryDatabaseI.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnImport.add(mntmInventoryDatabaseI);
		
		JMenuItem mntmWorkOrdersDatabaseI = new JMenuItem("Work Orders Database");
		mntmWorkOrdersDatabaseI.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnImport.add(mntmWorkOrdersDatabaseI);
		
		JMenu mnExport = new JMenu("Export");
		mnExport.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnFile.add(mnExport);
		
		JMenuItem mntmCustomersDatabaseE = new JMenuItem("Customers Database");
		mntmCustomersDatabaseE.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnExport.add(mntmCustomersDatabaseE);
		
		JMenuItem mntmInventoryDatabaseE = new JMenuItem("Inventory Database");
		mntmInventoryDatabaseE.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnExport.add(mntmInventoryDatabaseE);
		
		JMenuItem mntmWorkOrdersDatabaseE = new JMenuItem("Work Orders Database");
		mntmWorkOrdersDatabaseE.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnExport.add(mntmWorkOrdersDatabaseE);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				LoginWindow login = new LoginWindow();
				login.frmOverdrive.setLocationRelativeTo(null);
				login.frmOverdrive.setVisible(true);
				frmOverdrive.dispose();
			}
		});
		mntmLogOut.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnFile.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frmOverdrive.dispose();
			}
		});
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		mnView.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnHelp);
		
		JMenuItem mntmTipsAndTricks = new JMenuItem("Tips and Tricks");
		mntmTipsAndTricks.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnHelp.add(mntmTipsAndTricks);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				About about = new About();
				about.frmAboutOverdrive.setLocationRelativeTo(null);
				about.frmAboutOverdrive.setVisible(true);
			}
		});
		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnHelp.add(mntmAbout);
		
	}
	

// *****************************************************************     AUXILLARY METHODS     *****************************************************************
 
	
	
	// Refresh / Initialize Customer FList from the SQL DB
	void initializeCustomerView(){
		connection = sqliteConnection.dbConnector();
		try{
			String query = "select `First Name`, `Last Name`, `Phone Number`, `Customer ID` from customers";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet res = pst.executeQuery();
			customer_table.setModel(DbUtils.resultSetToTableModel(res));
			
			customer_table.getColumnModel().getColumn(0).setResizable(false);
			customer_table.getColumnModel().getColumn(1).setResizable(false);
			customer_table.getColumnModel().getColumn(2).setResizable(false);
			customer_table.getColumnModel().getColumn(3).setResizable(false);
			
			pst.close();
			res.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Refresh / Initialize Inventory List from the SQL DB
	void initializeInventoryView(){
		connection = sqliteConnection.dbConnector();
		try{
			String query = "select `Part Number`, `Part Description`, Vendor, `Store Location`, `In Stock`, `Unit Cost`, `In Stock` * `Unit Cost` as `Total Cost` from inventory";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet res = pst.executeQuery();
			inventory_table.setModel(DbUtils.resultSetToTableModel(res));
			
			inventory_table.getColumnModel().getColumn(0).setResizable(false);
			inventory_table.getColumnModel().getColumn(2).setResizable(false);
			inventory_table.getColumnModel().getColumn(3).setResizable(false);
			inventory_table.getColumnModel().getColumn(4).setResizable(false);
			inventory_table.getColumnModel().getColumn(5).setResizable(false);
			inventory_table.getColumnModel().getColumn(6).setResizable(false);
			
			pst.close();
			res.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Add a New Customer to the list and SQL DB
	
	
	// Delete a Customer from the list and SQL DB
	void delCustomer() {
		try{
			int row = customer_table.getSelectedRow();
			int id = (int) customer_table.getValueAt(row, 3);
			
			String query = "DELETE FROM customers WHERE `Customer ID` = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, id);
			
			pst.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Customer deleted");
			initializeCustomerView();
			clearNewCust();
			pst.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Delete Inventory Item from the list
	void delItem(){
		try{
			int row = inventory_table.getSelectedRow();
			String id= (String) inventory_table.getValueAt(row, 0);
			String query = "delete from inventory where `Part Number` = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, id);
			
			pst.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Inventory Item Deleted");
			initializeInventoryView();
			
			pst.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Clear new / search customer text boxes
	void clearNewCust(){
		firstName.setText("First name");
		lastName.setText("Last name");
		phoneNumber.setText("Phone number");
	}
	
	// Search Customer 
	// ALGORITHM DOESN'T WORK YET
	void searchCust(){
		int row = inventory_table.getSelectedRow();
		firstName.getText();
	}
}
