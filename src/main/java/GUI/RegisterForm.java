package GUI;

import DB.JDBConnection;
import common.ColorValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterForm  extends Form {
    public RegisterForm() {
        super("Register");
        addGUIComponents();
    }

    private void addGUIComponents() {
        // create register label
        JLabel registerLabel = new JLabel("Register");

        // configure component's x,y position and width/height values relative to the GUI
        registerLabel.setBounds(0, 15, 520, 100);

        // change the font color
        registerLabel.setForeground(ColorValue.TEXT_COLOR);

        // change the font size
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        // center text
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add component to GUI
        add(registerLabel);

        // create fullname label
        JLabel fullnameLabel = new JLabel("Full Name:");
        fullnameLabel.setBounds(30, 100, 400, 25);
        fullnameLabel.setForeground(ColorValue.TEXT_COLOR);
        fullnameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create fullname text field
        JTextField fullnameField = new JTextField();
        fullnameField.setBounds(30, 135, 450, 55);
        fullnameField.setBackground(ColorValue.SECONDARY_COLOR);
        fullnameField.setForeground(ColorValue.TEXT_COLOR);
        fullnameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(fullnameLabel);
        add(fullnameField);

        // create username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 200, 400, 25);
        usernameLabel.setForeground(ColorValue.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create username text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 235, 450, 55);
        usernameField.setBackground(ColorValue.SECONDARY_COLOR);
        usernameField.setForeground(ColorValue.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);

        // create password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 300, 400, 25);
        passwordLabel.setForeground(ColorValue.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create username text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 335, 450, 55);
        passwordField.setBackground(ColorValue.SECONDARY_COLOR);
        passwordField.setForeground(ColorValue.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        // create re-enter password label
        JLabel rePasswordLabel = new JLabel("Re-enter Password:");
        rePasswordLabel.setBounds(30, 400, 400, 25);
        rePasswordLabel.setForeground(ColorValue.TEXT_COLOR);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create username text field
        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(30, 435, 450, 55);
        rePasswordField.setBackground(ColorValue.SECONDARY_COLOR);
        rePasswordField.setForeground(ColorValue.TEXT_COLOR);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(rePasswordLabel);
        add(rePasswordField);

        // create login button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));

        // change the cursor to a hand when hover over the button
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(ColorValue.TEXT_COLOR);
        registerButton.setBounds(125, 520, 250, 50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String rePassword = new String(rePasswordField.getPassword());
                String fullName = fullnameField.getText();
                if(validateUserInput(username, password, rePassword, fullName)) {
                    if(JDBConnection.register(username, password, fullName)) {
                        RegisterForm.this.dispose();

                        LoginForm loginForm = new LoginForm();
                        loginForm.setVisible(true);

                        JOptionPane.showMessageDialog(loginForm, "Registered Account Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(RegisterForm.this, "Error: Username must be at least 6 characters \n" +
                                "and/or Passwords must match");
                    }
                }
            }
        });
        add(registerButton);

        // create register label (used to load the register GUI)
        JLabel loginLabel = new JLabel("Have an account?   Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(ColorValue.TEXT_COLOR);
        loginLabel.setBounds(125, 600, 250, 30);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterForm.this.dispose();

                new LoginForm().setVisible(true);
            }
        });
        add(loginLabel);
    }

    private boolean validateUserInput(String username, String password, String rePassword, String fullName) {
        if(username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || fullName.isEmpty()) return false;

        if(!password.equals(rePassword)) return false;


        return true;
    }
}

