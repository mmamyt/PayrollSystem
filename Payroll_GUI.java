import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Payroll_GUI extends JFrame {
	private JTextField usernameText = new JTextField();
	private JTextField passwordText = new JTextField();
	private JButton loginBTN = new JButton("Login");
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Payroll_GUI()
	{
		setPreferredSize(new Dimension(700,700));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel usernameLabel = new JLabel("Username");
		JLabel password = new JLabel("Password");
	}

}
