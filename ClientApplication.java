import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClientApplication {
    private JFrame frame;
    private JTextField englishText;
    private JComboBox<String> targetLanguage;
    private JButton translateButton;
    
    public ClientApplication() {
        frame = new JFrame("Translation Application");
        frame.setLayout(new FlowLayout());
        englishText = new JTextField("Enter English text");
        frame.add(englishText);
        targetLanguage = new JComboBox<String>();
        targetLanguage.addItem("English");
        targetLanguage.addItem("Korean");
        targetLanguage.addItem("Spanish");
        frame.add(targetLanguage);
        translateButton = new JButton("Translate");
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    @SuppressWarnings("resource")
					Socket socket = new Socket("localhost", 8080);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(englishText.getText());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    String translatedText = (String) in.readObject();
                    englishText.setText(translatedText);
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.add(translateButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new ClientApplication();
    }
}
