import Frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Function;


public class JButtonApp extends JFrame implements ActionListener{

    private JButton loginButton;
    private JButton registerButton;


    public JButtonApp() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.text();



        loginButton = new JButton("Login"); //creates button
        loginButton.addActionListener(this);   // gives the button an action
        mainFrame.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        mainFrame.add(registerButton);

        mainFrame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            Functions.Login();
        }

        if(e.getSource() == registerButton){
            Functions.Register();
        }

    }
}
