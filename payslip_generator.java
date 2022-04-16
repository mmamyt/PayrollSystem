package payslip_generator;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.*;

public class payslip_generator extends JFrame implements ActionListener {
	private JTextField payperiodText = new JTextField(20);
	private JButton addBTN = new JButton("ADD");
	private JButton generateBTN = new JButton("Generate Payslip for all");

	
	public static void main(String[] args) {
		new payslip_generator();		

	}
	public payslip_generator(){
	
		Container cp = getContentPane();
		JPanel titlePanel = new JPanel();
		JPanel bodyPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,70,10);
		cp.setLayout(new BorderLayout());
		setMinimumSize(new Dimension(400,400));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel payslipgenTitle = new JLabel("Payslip Generator");
		payslipgenTitle.setFont(new Font("Serif", Font.BOLD, 34));
		titlePanel.add(payslipgenTitle);
			c.fill = GridBagConstraints.HORIZONTAL;
		payperiodText.setEditable(true);
		c.weightx = 0.7;
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 20;
		c.gridwidth = 2;
		bodyPanel.add(payperiodText, c);
		c.weightx = 0.3;
		c.gridx = 3;
		c.gridy = 0;
		c.ipady = 20;
		JButton addb = new JButton("ADD");
		addb.addActionListener(new ActionListener(){
			String payperiod;
		            public void actionPerformed(ActionEvent e){
		            	String pay_period = payperiodText.getText();
		            	payperiod = pay_period;
		            	payperiodText.setText("");
		            }});
		bodyPanel.add(addb, c);
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		JButton generateBTN = new JButton("Generate Payslip for all");
		generateBTN.addActionListener(new ActionListener(){
		            public void actionPerformed(ActionEvent e){
		            }});
		bodyPanel.add(generateBTN, c);
		cp.add(titlePanel, BorderLayout.NORTH);
		cp.add(bodyPanel, BorderLayout.CENTER);

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void getemployeeinfo(){
		payslip_generator connect1=new payslip_generator();
		try {
			Scanner sc = new Scanner(System.in);
			Connection c1 = connect1.getConnection();
			System.out.println("Connection to SQLite has been established");
			Statement s1 = c1.createStatement();
			ResultSet rs = s1.executeQuery("Select * from Accounts ");
			while (rs.next()) {
		         int id = rs.getInt("ID");
		         String f_name = rs.getString("First_Name");
		         String l_name = rs.getString("Last_Name");
		         Double sal  = rs.getDouble("Salary");
		         String dep = rs.getString("Department");
		         int ssn = rs.getInt("SSN");
		         String email = rs.getString("Email");
		         System.out.println( "ID = " + id);
		         System.out.println( "First Name = " + f_name);
		         System.out.println( "Last Name = " + l_name);
		         System.out.println( "Salary = " + sal);
		         System.out.println( "Department = " + dep);
		         System.out.println( "SSN = " + ssn);
		         System.out.println( "Email = " + email);

		         System.out.println();
		      }
			rs.close();
			s1.close();
			c1.commit();
			c1.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:sqlite:";
		Connection connection=DriverManager.getConnection(dbUrl);
		return connection;
	}

	}