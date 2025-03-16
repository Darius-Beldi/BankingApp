//The applications first or the main frame
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private JButton myFirstButton;
    private JButton mySecondButton;

    // Constructor for a new frame

    public MainFrame (){
        super("My First Button Program");
        myFirstButton = new JButton("First Frame");
        myFirstButton.setFont(new Font( "Arial", Font.BOLD, 18));
        myFirstButton.setBackground(Color.red);

        mySecondButton = new JButton("New Frame");
        mySecondButton.setFont(new Font( "Arial", Font.BOLD, 18));
        mySecondButton.setBackground(Color.green);

        Container c = getContentPane();
        FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
        c.setLayout(fl);

        c.add (myFirstButton);
        c.add (mySecondButton);

        ButtonHandler handler = new ButtonHandler();    //creation of a new Object
        myFirstButton.addActionListener(handler);          // Attach/register handler to myFirstButton
        mySecondButton.addActionListener(handler);        //Attach/register handler to mySecondButton

        setSize(400, 300);
        show();
    }


    public static void main(String [] args) {

        // Make frame
        MainFrame f = new MainFrame();

        f.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {

                        // This closes the window and terminates the
                        // Java Virtual Machine in the event that the
                        // Frame is closed by clicking on X.
                        System.out.println("Exit via windowClosing.");
                        System.exit(0);
                    }
                }
        );
    } // end of main

    // inner class for button event handling
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == myFirstButton) {
                new NewFrame1();

            }
            if (e.getSource() == mySecondButton) {
                new NewFrame2();
            }
        }
    } // end of inner class
} // end of outer class

