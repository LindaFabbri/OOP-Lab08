package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final JFrame frame = new JFrame();
    private static final int PROPORTION = 2;

    public SimpleGUIWithFileChooser() {
        final JPanel myPanel = new JPanel();
        final JTextArea myArea = new JTextArea();
        myPanel.setLayout(new BorderLayout());
        final JButton save = new JButton("Save");

        Controller myController = new Controller();
        final JPanel myPanel2 = new JPanel();
        myPanel2.setLayout(new BorderLayout());
        final JTextField myTextField = new JTextField(myController.getMyFile().toString()); // si poteva anche fare new
                                                                                            // JTextField(myController.getPath(myController.getMyFile()))
        myTextField.setEditable(false); // per fare in modo che myTextField non sia modificabile
        final JButton browse = new JButton("Browse");

        myPanel2.add(myTextField, BorderLayout.CENTER);
        myPanel2.add(browse, BorderLayout.LINE_END);

        myPanel.add(save, BorderLayout.SOUTH);
        myPanel.add(myPanel2, BorderLayout.NORTH);
        myPanel.add(myArea);

        frame.setContentPane(myPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    myController.addString(myArea.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ;
            }
        });

        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    myController.setMyFile(chooser.getSelectedFile());
                    // myTextField.setText(chooser.getSelectedFile().toString());
                    myTextField.setText(myController.getPath(myController.getMyFile()));
                } else if (returnVal == JFileChooser.CANCEL_OPTION) {

                } else {
                    JOptionPane.showMessageDialog(null, "An error is occured");
                }
            }
        });

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser();
    }
}

/*
 * TODO: Starting from the application in mvcio:
 * 
 * 1) Add a JTextField and a button "Browse..." on the upper part of the
 * graphical interface. Suggestion: use a second JPanel with a second
 * BorderLayout, put the panel in the North of the main panel, put the text
 * field in the center of the new panel and put the button in the line_end of
 * the new panel.
 * 
 * 2) The JTextField should be non modifiable. And, should display the current
 * selected file.
 * 
 * 3) On press, the button should open a JFileChooser. The program should use
 * the method showSaveDialog() to display the file chooser, and if the result is
 * equal to JFileChooser.APPROVE_OPTION the program should set as new file in
 * the Controller the file chosen. If CANCEL_OPTION is returned, then the
 * program should do nothing. Otherwise, a message dialog should be shown
 * telling the user that an error has occurred (use
 * JOptionPane.showMessageDialog()).
 * 
 * 4) When in the controller a new File is set, also the graphical interface
 * must reflect such change. Suggestion: do not force the controller to update
 * the UI: in this example the UI knows when should be updated, so try to keep
 * things separated.
 */
