package payslip_generator;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import javax.swing.*;

class payroll_data {
	int id;
    String f_name;
    String l_name;
    String role;
    Double sal;
    String dep;
    int ssn;
    String email;
    
}

@SuppressWarnings("serial")
public class payslip_generator extends JFrame implements ActionListener {
	private JTextField payperiodText = new JTextField(20);
	private JButton addBTN = new JButton("ADD");
	private JButton generateBTN = new JButton("Generate Payslip for all");
	private payroll_data[] payslipdata;
	
	public static void main(String[] args) {
		new payslip_generator();		

	}
	public payslip_generator(){
	
		Container cp = getContentPane();
		JPanel titlePanel = new JPanel();
		JPanel bodyPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		cp.setLayout(new BorderLayout());
		setMinimumSize(new Dimension(400,400));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel payperiodinfo = new JLabel("Use format (YYYY-MM-DD:YYYY-MM-DD)");
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
		            	payperiodText.setText("");
		            	payperiod = pay_period;
		            	Long numweeks = calculatepayperiod(payperiod);
		            	System.out.println(numweeks);
		            }});
    	
		bodyPanel.add(addb, c);
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		JButton generateBTN = new JButton("Generate Payslip for all");
		generateBTN.addActionListener(new ActionListener(){
		            public void actionPerformed(ActionEvent e){
		            }});
		bodyPanel.add(generateBTN, c);
		c.gridx = 1;
		c.gridy = 1;
		bodyPanel.add(payperiodinfo,c);
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
        payslipdata = new payroll_data[100];
		try {
			Scanner sc = new Scanner(System.in);
			Connection c1 = connect1.getConnection();
			System.out.println("Connection to SQLite has been established");
			Statement s1 = c1.createStatement();
			ResultSet rs = s1.executeQuery("Select * from Accounts ");
			while (rs.next()) {
				int i = 0;
		         int id = rs.getInt("ID");
		         String f_name = rs.getString("First_Name");
		         String l_name = rs.getString("Last_Name");
		         String role = rs.getString("Role");
		         Double sal  = rs.getDouble("Salary");
		         String dep = rs.getString("Department");
		         int ssn = rs.getInt("SSN");
		         String email = rs.getString("Email");
		         payroll_data payroll = new payroll_data();
		         payroll.id = id;
		         payroll.f_name = f_name;
		         payroll.l_name = l_name;
		         payroll.role = role;
		         payroll.sal = sal;
		         payroll.dep = dep;
		         payroll.ssn = ssn;
		         payroll.email = email;
		         payslipdata[i] = payroll;
		         System.out.println( "ID = " + id);
		         System.out.println( "First Name = " + f_name);
		         System.out.println( "Last Name = " + l_name);
		         System.out.println( "Role = " + role);
		         System.out.println( "Salary = " + sal);
		         System.out.println( "Department = " + dep);
		         System.out.println( "SSN = " + ssn);
		         System.out.println( "Email = " + email);
		         System.out.println(i);
		         i++;
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
	public long calculatepayperiod(String dates) {
		String[] parts = dates.split(":");
		String date1 = parts[0];
		String date2 = parts[1];
		LocalDate dateBefore = LocalDate.parse(date1);
		LocalDate dateAfter = LocalDate.parse(date2);
		long noOfWeeksBetween = ChronoUnit.WEEKS.between(dateBefore, dateAfter);
		return noOfWeeksBetween;
	}

	}