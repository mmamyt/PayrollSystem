import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PageFour extends javax.swing.JFrame{
    private JFrame frame;
    private JPanel panel;
    private JLabel labelID, labelFN, labelLN, labelRole, labelSDate, labelEDate, labelSalary, labelDepartment, labelAddress, labelEmail;
    private JTextField ID, firstName, lastName, role, startDate, endDate, salary, department, Address, email;
    private javax.swing.JButton buttonUpdate;

    public PageFour(){
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        //ID
        labelID = new JLabel("ID:");
        labelID.setBounds(20, 40, 100, 25);
        panel.add(labelID);
        ID = new JTextField();
        ID.setBounds(100, 40, 150, 25);
        panel.add(ID);

        //First Name
        labelFN = new JLabel("First Name:");
        labelFN.setBounds(20, 70, 100, 25);
        panel.add(labelFN);
        firstName = new JTextField();
        firstName.setBounds(100, 70, 150, 25);
        panel.add(firstName);

        //Last Name
        labelLN = new JLabel("Last Name:");
        labelLN.setBounds(20, 100, 100, 25);
        panel.add(labelLN);
        lastName = new JTextField();
        lastName.setBounds(100, 100, 150, 25);
        panel.add(lastName);

        //Address
        labelAddress = new JLabel("Address:");
        labelAddress.setBounds(20, 130, 100, 25);
        panel.add(labelAddress);
        Address = new JTextField();
        Address.setBounds(100, 130, 150, 25);
        panel.add(Address);

        //Start date
        labelSDate = new JLabel("Start Date:");
        labelSDate.setBounds(20, 160, 100, 25);
        panel.add(labelSDate);
        startDate = new JTextField();
        startDate.setBounds(100, 160, 150, 25);
        panel.add(startDate);

        //End Date
        labelEDate = new JLabel("End Date:");
        labelEDate.setBounds(300, 40, 100, 25);
        panel.add(labelEDate);
        endDate = new JTextField();
        endDate.setBounds(380, 40, 150, 25);
        panel.add(endDate);

        //Role
        labelRole = new JLabel("Role:");
        labelRole.setBounds(300, 70, 100, 25);
        panel.add(labelRole);
        role = new JTextField();
        role.setBounds(380, 70, 150, 25);
        panel.add(role);

        //Department
        labelDepartment = new JLabel("Department:");
        labelDepartment.setBounds(300, 100, 100, 25);
        panel.add(labelDepartment);
        department = new JTextField();
        department.setBounds(380, 100, 150, 25);
        panel.add(department);

        //Salary
        labelSalary = new JLabel("Salary:");
        labelSalary.setBounds(300, 130, 100, 25);
        panel.add(labelSalary);
        salary = new JTextField();
        salary.setBounds(380, 130, 150, 25);
        panel.add(salary);

        //email
        labelEmail = new JLabel("email:");
        labelEmail.setBounds(300, 160, 100, 25);
        panel.add(labelEmail);
        email = new JTextField();
        email.setBounds(380, 160, 150, 25);
        panel.add(email);

        //button
        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(380, 220, 150, 25);
        panel.add(buttonUpdate);
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "UPDATE Employees " +
                            "SET First_Name = '"+ firstName.getText() + "', Last_Name = '" + lastName.getText() + "', role ='" + role.getText() +
                            "', StartDate = '" + startDate.getText() + "', EndDate = " + endDate.getText() + ", salary = "+
                            salary.getText() + ", department = '" + department.getText() + "', Address = '" + Address.getText() + "', email ='" + email.getText() +"' WHERE id ="+ ID.getText();
                    Connection c4 = getConnection();
                    Statement s4 = c4.createStatement();
                   /* PreparedStatement ps = c4.prepareStatement(query);
                    ps.setString(1, role.getText());
                    ps.setDouble(2, Double.parseDouble(salary.getText()));
                    ps.setString(3, department.getText());
                    ps.setDate(4, Date.valueOf(endDate.getText()));*/
                    int rowCount = s4.executeUpdate(query);
                }catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        PageFour pf = new PageFour();
    }

    public static Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite:C:\\SQLiteStudio\\PayrollSystem";
        Connection connection = DriverManager.getConnection(dbUrl);
        return connection;
    }
}