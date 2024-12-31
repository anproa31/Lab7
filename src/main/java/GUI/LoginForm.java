package GUI;

import DB.JDBConnection;
import common.ColorValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends Form{
    public LoginForm() {
        super("Login");
        addGUIComponents();
    }

    private void addGUIComponents() {
        //create Login label
        JLabel loginLabel = new JLabel("Login");

        //configure components position - width - height
        loginLabel.setBounds(0, 25, 520, 100);

        //change the font color
        loginLabel.setForeground(ColorValue.TEXT_COLOR);

        //change the font size
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        //center text
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //add components to GUI
        add(loginLabel);

        //create user_name label
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(ColorValue.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        //create user_name text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(ColorValue.SECONDARY_COLOR);
        usernameField.setForeground(ColorValue.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);

        //create password label
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(30, 335, 400, 25);
        passwordLabel.setForeground(ColorValue.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        //create user_name text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 365, 450, 55);
        passwordField.setBackground(ColorValue.SECONDARY_COLOR);
        passwordField.setForeground(ColorValue.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        //create login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));

        //change the cursor to a hand when hover over the button
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(ColorValue.TEXT_COLOR);
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if(JDBConnection.validateLogin(username, password)) {
                    String fullName = JDBConnection.getFullName(username);
                    JOptionPane.showMessageDialog(LoginForm.this, "Login Successfully! " + fullName);
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password.");
                }
            }
        });
        add(loginButton);


        //create register label to load the register GUI
        JLabel registerLabel = new JLabel("Not a user?   Register here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(ColorValue.TEXT_COLOR);
        registerLabel.setBounds(125, 600, 250, 30);
        // add functionality so that when clicked it will launch the register form gui
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this GUI
                LoginForm.this.dispose();

                // launch the register gui
                new RegisterForm().setVisible(true);
            }
        });
        add(registerLabel);
    }
}
