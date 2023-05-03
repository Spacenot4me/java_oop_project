package GUI;



import BackendCode.DB_manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static BackendCode.DB_manager.connection;


public class Registration extends JFrame implements Runnable{
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 300;

    private JPanel registerPanel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel nameLabel, emailLabel, usernameLabel, passwordLabel;

    public JPanel getregisterPanel() {
        return registerPanel;
    }
    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(){
        try{
           //Сбросить инкримент TRUNCATE TABLE tbl RESTART IDENTITY;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, email, username, password) VALUES (?, ?, ?, ?)");
            statement.setString(1, nameField.getText());
            statement.setString(2, emailField.getText());
            statement.setString(3, usernameField.getText());
            statement.setString(4, passwordField.getText());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "User added successfully");
        }catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        }






    @Override
    public void run() {
        setTitle("Registration");

        JLabel heading = new JLabel("Create a new account");
        heading.setFont(new Font("Verdana", Font.BOLD, 20));

        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DB_manager.connect();
                addUser();
                dispose();
                closeConnection(connection);
            }

        });



        registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registerPanel.add(heading, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        registerPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        registerPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        registerPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        registerPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        registerPanel.add(registerButton, gbc);

        getContentPane().add(registerPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    public static void start_gui() {
        Runnable gui = new Registration();

        Thread guiThread = new Thread(gui);
        guiThread.run();
    }


    public class main {
        public static void main(String[] args) {

        }
    }
}


