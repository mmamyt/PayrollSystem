import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PageThree extends javax.swing.JFrame {

    private JFrame frame;
    private JPanel panel;
    private JLabel labelID, labelFN, labelLN, labelRole, labelSDate, labelSalary, labelDepartment, labelAddress, labelEmail, success;
    private JTextField ID, firstName, lastName, role, startDate, salary, department, Address, email;
    private javax.swing.JButton buttonAdd;

    Connection connection = null;

    public PageThree() {

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

        //success message
        success = new JLabel(" ");
        success.setBounds(300,220,150,25);
        frame.setVisible(true);

        //button
        buttonAdd = new JButton("Add New Record");
        buttonAdd.setBounds(380, 220, 150, 25);
        panel.add(buttonAdd);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!ID.getText().isEmpty() && !firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !role.getText().isEmpty() && !startDate.getText().isEmpty() &&
                        !salary.getText().isEmpty() && !department.getText().isEmpty() && !Address.getText().isEmpty() && !email.getText().isEmpty()) {
                    try {
                        String query = "INSERT INTO Employees (ID, First_Name, Last_Name, Role, Salary, StartDate, EndDate, Department, Address, email) VALUES ('" +
                                ID.getText() + "','" + firstName.getText() + "','" + lastName.getText() + "','" + role.getText() + "'," + salary.getText()
                                + ", '" + startDate.getText() + "', 'null', '" + department.getText() + "','" + Address.getText() + "','" + email.getText() + "')";
                        Connection c3 = getConnection();
                        Statement s3 = c3.createStatement();
                        int rowCount = s3.executeUpdate(query);
                        //setting cells to null
                        ID.setText("");
                        firstName.setText("");
                        lastName.setText("");
                        role.setText("");
                        startDate.setText("");
                        salary.setText("");
                        department.setText("");
                        Address.setText("");
                        email.setText("");
                        success.setText("New Employee details were added");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }else {
                    System.out.println("Please enter all fields");
                }
            }
        });

    }

    public static void main(String[] args) {
        PageThree pt = new PageThree();
    }

    public static Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite:C:\\SQLiteStudio\\PayrollSystem";
        Connection connection = DriverManager.getConnection(dbUrl);
        return connection;
    }

}