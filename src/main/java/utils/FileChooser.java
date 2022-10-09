package utils;

import javax.swing.*;
import java.awt.event.*;

public abstract class FileChooser extends JFrame implements ActionListener {
    public static JLabel jLabel;
    public static JFrame jFrame;
    public String file = "";

    public void openFileChooser(FileChooser fileChooser) {
        jFrame = new JFrame("file chooser");
        jFrame.setSize(400, 200);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton saveButton = new JButton("save");
        JButton openButton = new JButton("open");
        saveButton.addActionListener(fileChooser);
        openButton.addActionListener(fileChooser);

        JPanel jPanel = new JPanel();
        jPanel.add(saveButton);
        jPanel.add(openButton);

        jLabel = new JLabel("no file selected");
        jPanel.add(jLabel);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    abstract public void actionPerformed(ActionEvent evt);

}
