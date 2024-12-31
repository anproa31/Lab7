package GUI;

import common.ColorValue;

import javax.swing.*;


public class Form extends JFrame {
    //create constructor
    public Form(String title) {
        super(title);

        //set the size of the GUI
        setSize(520, 680);

        //configure GUI to end process after closing
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //set layout to null to disable layout management so we can absolute positioning
        //to place the components wherever u want
        setLayout(null);

        //Load GUI in the center of the screen
        setLocationRelativeTo(null);

        //prevent UI from changing size
        setResizable(false);

        //change background color
        getContentPane().setBackground(ColorValue.PRIMARY_COLOR);
    }
}
