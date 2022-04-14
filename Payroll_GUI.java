import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Payroll_GUI extends JFrame {

	private JTextField usernameText = new JTextField(20);
	private JTextField passwordText = new JTextField(20);
	private JButton loginBTN = new JButton("Login");
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Payroll_GUI();
	}
	
	public Payroll_GUI()
	{
		super("PayHero");
		Container cp = getContentPane();
		JPanel titlePanel = new JPanel();
		JPanel bodyPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		cp.setLayout(new BorderLayout());
		setMinimumSize(new Dimension(400,400));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		cp.add(titlePanel, BorderLayout.NORTH);
		cp.add(bodyPanel, BorderLayout.CENTER);

		setVisible(true);
	}

}
