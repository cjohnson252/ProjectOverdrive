import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class About extends mainMenu{

	JFrame frmAboutOverdrive;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About window = new About();
					window.frmAboutOverdrive.setLocationRelativeTo(null);
					window.frmAboutOverdrive.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize About window
	 */
	public About() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAboutOverdrive = new JFrame();
		frmAboutOverdrive.setTitle("About Overdrive");
		frmAboutOverdrive.setBounds(100, 100, 541, 380);
		frmAboutOverdrive.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmAboutOverdrive.getContentPane().setLayout(null);
		
		JLabel lblOverdrive = new JLabel("Overdrive");
		lblOverdrive.setForeground(new Color(0, 0, 0));
		lblOverdrive.setHorizontalAlignment(SwingConstants.CENTER);
		lblOverdrive.setFont(new Font("Corbel", Font.BOLD, 30));
		lblOverdrive.setBounds(10, 169, 505, 56);
		frmAboutOverdrive.getContentPane().add(lblOverdrive);
		
		JLabel lblVersion = new JLabel("Version 1.1.0");
		lblVersion.setFont(new Font("Corbel", Font.PLAIN, 15));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersion.setBounds(10, 222, 505, 38);
		frmAboutOverdrive.getContentPane().add(lblVersion);
		
		JLabel lblMadeByFil = new JLabel("Made by Fil :)");
		lblMadeByFil.setFont(new Font("Corbel", Font.PLAIN, 15));
		lblMadeByFil.setHorizontalAlignment(SwingConstants.CENTER);
		lblMadeByFil.setBounds(10, 283, 505, 56);
		frmAboutOverdrive.getContentPane().add(lblMadeByFil);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("C:\\Users\\F\\Documents\\OVERDRIVE\\smallicon.png"));
		label.setBounds(10, 46, 505, 128);
		frmAboutOverdrive.getContentPane().add(label);
	}
}
