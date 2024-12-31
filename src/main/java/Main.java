import GUI.LoginForm;
import GUI.RegisterForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
//                new RegisterForm("Register").setVisible(true);
            }
        });
    }
}
