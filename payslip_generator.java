package payslip_generator;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.*;


class payroll_data {
	int id;
    String f_name;
    String l_name;
    String role;
    Double sal;
    String dep;
    String email;
    String address;
    
}

@SuppressWarnings("serial")
public class payslip_generator extends JFrame implements ActionListener {
	private JTextField payperiodText = new JTextField(20);
	private JButton addBTN = new JButton("ADD");
	private JButton generateBTN = new JButton("Generate Payslip for all");
	private payroll_data[] payslipdata;
	private String payperiod;
	private Long Num_weeks;
	
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
		setMinimumSize(new Dimension(700,450));
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
		addBTN = new JButton("ADD");
		
		addBTN.addActionListener(new ActionListener(){
		            public void actionPerformed(ActionEvent e){
		            	String pay_period = payperiodText.getText();
		            	payperiodText.setText("");
		            	payperiod = pay_period;
		            	Long numweeks = calculatepayperiod(payperiod);
		            	Num_weeks = numweeks;
		            	System.out.println(numweeks);
		            }});
    	
		bodyPanel.add(addBTN, c);
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		generateBTN = new JButton("Generate Payslip for all");
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
		         String add = rs.getString("Address");
		         String email = rs.getString("Email");
		         payroll_data payroll = new payroll_data();
		         payroll.id = id;
		         payroll.address = add;
		         payroll.f_name = f_name;
		         payroll.l_name = l_name;
		         payroll.role = role;
		         payroll.sal = sal;
		         payroll.dep = dep;
		         payroll.email = email;
		         payslipdata[i] = payroll;
		         System.out.println( "ID = " + id);
		         System.out.println( "First Name = " + f_name);
		         System.out.println( "Last Name = " + l_name);
		         System.out.println( "Role = " + role);
		         System.out.println( "Salary = " + sal);
		         System.out.println( "Department = " + dep);
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
		String dbUrl = "jdbc:sqlite:C:\\SQLiteStudio\\PayrollSystem";
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
	void Writepayslip(payroll_data[] param) throws IOException {
		int i = 0;
		do {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		String dirString=s+"/data";
		Path dirPath=Paths.get(dirString);
		if (Files.notExists(dirPath)){
		Files.createDirectories(dirPath);
		}
		
		String fileString= param[i].l_name + param[i].f_name + "Payslip.txt";
		Path filePath=Paths.get(dirString,fileString);
		if (Files.notExists(filePath)){
			Files.createFile(filePath);
		}
		if (Files.exists(dirPath)&&Files.isDirectory(dirPath)){
			System.out.println("Directory: "
					+dirPath.toAbsolutePath());
			System.out.println("Files: ");
			DirectoryStream<Path> dirStream=
					Files.newDirectoryStream(dirPath);
			for (Path p:dirStream){
				System.out.println("    "+p.getFileName());
			}
		}
		Path payslipPath=Paths.get(dirString, fileString);
		File payslipFile=payslipPath.toFile();
		System.out.println(payslipFile);
		PrintWriter accwriter=new PrintWriter(
				new BufferedWriter(
				new FileWriter(payslipFile)));
		accwriter.println("");
		accwriter.println("Company: Disney Gold Mining and Co");
		accwriter.println("12345 Mainstreet, Chicago IL");
		accwriter.println("");
		accwriter.println("Payslip for: " + param[i].f_name + " " + param[i].l_name);
		accwriter.println("Role: " + param[i].role);
		accwriter.println("Address: " + param[i].address);
		accwriter.println("");
		accwriter.println("Pay Period for the following dates: " + payperiod);
		accwriter.println("Pay Type: BiWeekly");
		accwriter.println("Salary to be paid: " + param[i].sal * (Num_weeks/2) + "Gold Nuggets");
		accwriter.println("");
	accwriter.close();
	i++;
	}
		while (param[i] != null);
	}
	}