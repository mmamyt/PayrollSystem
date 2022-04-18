import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Payroll_GUI extends JFrame {

	private JFrame loginPage = new JFrame("PayHero");
	private String username = "robert";
	private String password = "password";
	private JTextField usernameText = new JTextField(20);
	private JTextField passwordText = new JTextField(20);
	private JButton loginBTN = new JButton("Login");
	HomePageGUI hpgui;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Payroll_GUI();
	}
	
	public Payroll_GUI()
	{
		//setTitle("PayHero");
		//Container cp = getContentPane();
		JPanel titlePanel = new JPanel();
		JPanel bodyPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		//cp.setLayout(new BorderLayout());
		loginPage.setLayout(new BorderLayout());
		loginPage.setMinimumSize(new Dimension(400,400));
		loginPage.setLocationByPlatform(true);
		loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JLabel payHeroTitle = new JLabel("PayHero");
		payHeroTitle.setFont(new Font("Dark Future", Font.BOLD, 34));
		titlePanel.add(payHeroTitle);
				
		JLabel usernameLabel = new JLabel("Username: ");
		c.gridx = 0;
		c.gridy = 0;
		bodyPanel.add(usernameLabel, c);
				
				
		usernameText.setEditable(true);
		c.gridx = 1;
		bodyPanel.add(usernameText, c);

		JLabel passwordLabel = new JLabel("Password: ");
		c.gridx = 0;
		c.gridy = 1;
		bodyPanel.add(passwordLabel, c);
				
		passwordText.setEditable(true);
		c.gridx = 1;
		bodyPanel.add(passwordText, c);
				
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		bodyPanel.add(loginBTN, c);
				
		ActionListener1 aListen = new ActionListener1();
		loginBTN.addActionListener(aListen);
				
		loginPage.add(titlePanel, BorderLayout.NORTH);
		loginPage.add(bodyPanel, BorderLayout.CENTER);

		loginPage.setVisible(true);
	}
	
	class ActionListener1 implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(usernameText.getText().contains(username) && passwordText.getText().contains(password))
			{
				loginPage.dispose();
				new HomePageGUI();
			}
		}
	}
}

class HomePageGUI extends JFrame{
	
	private JFrame homePage = new JFrame("PayHero");
	private JTextField sEIDText = new JTextField(15);
	private JButton searchBTN = new JButton("Search");
	private JButton newEmpBTN = new JButton("New Employee Record");
	private JButton calcSalBTN = new JButton("Calculate Salary");
	
	public HomePageGUI()
	{
		//setTitle("Home Page");
		//Container cp = getContentPane();
		//cp.setLayout(new BorderLayout());
		homePage.setLayout(new BorderLayout());
		JPanel centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		homePage.setMinimumSize(new Dimension(500,500));
		homePage.setLocationByPlatform(true);
		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		sEIDText.setEditable(true);
		c.gridx = 0;
		c.gridy = 0;
		centerPanel.add(sEIDText);
		
		c.gridx = 1;
		centerPanel.add(searchBTN);
		
		
		c.gridx = 0;
		c.gridy = 1;
		centerPanel.add(newEmpBTN, c);
				
		c.gridx = 1;
		c.gridy = 1;
		centerPanel.add(calcSalBTN, c);
		
		ActionListener2 aListen2 = new ActionListener2();
		searchBTN.addActionListener(aListen2);
		newEmpBTN.addActionListener(aListen2);
		calcSalBTN.addActionListener(aListen2);
		
		homePage.add(centerPanel, BorderLayout.CENTER);
		homePage.setVisible(true);
	}
	
	class ActionListener2 implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(evt.getSource() == searchBTN)
			{
				
			}
			
			if(evt.getSource() == newEmpBTN)
			{
				
			}
			
			if(evt.getSource() == calcSalBTN)
			{
				
			}
		}
	}	
}


