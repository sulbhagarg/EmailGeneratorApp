package emailApp;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class EmailApp {

	private JFrame frame;
	private JTextField tfFisrtName;
	private JTextField tfLastName;
	private JTextField tfContact;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailApp window = new EmailApp();
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
	public EmailApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		tfFisrtName = new JTextField();
		tfFisrtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfFisrtName.setBounds(120, 10, 318, 19);
		frame.getContentPane().add(tfFisrtName);
		tfFisrtName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 13, 100, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(10, 39, 100, 19);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(10, 68, 100, 19);
		frame.getContentPane().add(lblDepartment);
		
		tfLastName = new JTextField();
		tfLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfLastName.setBounds(120, 39, 318, 19);
		frame.getContentPane().add(tfLastName);
		tfLastName.setColumns(10);
		
		final JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"HR", "Tech", "Finance", "Analyst"}));
		cbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDepartment.setToolTipText("");
		cbDepartment.setBounds(120, 67, 318, 21);
		frame.getContentPane().add(cbDepartment);
		
		JButton btnGenerateEmail = new JButton("Generate Email");
		btnGenerateEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfFisrtName.getText().toString().isEmpty() || tfLastName.getText().toString().isEmpty() || tfContact.getText().toString().isEmpty())
					JOptionPane.showMessageDialog(null, "Please Enter All the Required Fields!!");
				else {
					String firstName = tfFisrtName.getText().toString().trim();
					String lastName = tfLastName.getText().toString().trim();
					String depChoice = cbDepartment.getSelectedItem().toString().trim();
					String contact = tfContact.getText().toString().trim();
					Email email = new Email(firstName, lastName,depChoice, contact);
					JOptionPane.showMessageDialog(null, email.showInfo());
				}
			}
		});
		btnGenerateEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGenerateEmail.setBounds(10, 125, 428, 28);
		frame.getContentPane().add(btnGenerateEmail);
		
		JLabel lblContactNo = new JLabel("Contact No");
		lblContactNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContactNo.setBounds(10, 97, 100, 13);
		frame.getContentPane().add(lblContactNo);
		
		tfContact = new JTextField();
		tfContact.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfContact.setBounds(120, 96, 318, 19);
		frame.getContentPane().add(tfContact);
		tfContact.setColumns(10);
		frame.setBounds(100, 100, 459, 197);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
