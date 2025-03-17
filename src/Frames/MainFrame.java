package Frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

        /// constructor for the MainFrame class
        public MainFrame() {
            /// name of the frame
            super("BankingApp");

            /// setting the default close operation
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            /// size of the frame
            setSize(414, 736);

            /// setting the layout of the frame
            setLayout(new FlowLayout(FlowLayout.LEADING));

            /// centers the window
            setLocationRelativeTo(null);

            /// cannot resize the window
            setResizable(false);


            /// setting the frame to be visible
            setVisible(true);


            //ImageIcon icon = new ImageIcon("images\\icon.png");


        }

        public void text(){
            Color text_color = Color.decode("#FF0000");
            JLabel label = new JLabel();
            add(label);

            label.setText("Welcome to Banking App");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.TOP);
            label.setForeground(text_color);
            label.setFont(new Font("Arial", Font.BOLD, 20));

            JLabel label2 = new JLabel();
            add(label2);
            label2.setText("Please select an option");
            label2.setHorizontalAlignment(JLabel.CENTER);
            label2.setVerticalAlignment(JLabel.TOP);
            label2.setForeground(text_color);
            label2.setFont(new Font("Arial", Font.BOLD, 20));
            //label.setVisible(False);

            setVisible(true);
        }

}
