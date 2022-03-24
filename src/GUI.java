import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;

public class GUI extends JFrame {

    private JTextField textField;
    private JLabel label;
    private JButton button;

    void displayGUI() {
        textField = new JTextField("Word Predictor");
        getContentPane().add(textField, BorderLayout.CENTER);
        label = new JLabel("Label");
        getContentPane().add(label, BorderLayout.SOUTH);
        // button = new JBotton("ok");
        textField.getDocument().addDocumentListener(new DocumentListener() {
            private String provideUpdatedText() {
                return textField.getText();
            }
            @Override
            public void changedUpdate(DocumentEvent arg0) {
                provideUpdatedText();
            }
            @Override 
            public void insertUpdate(DocumentEvent arg0) {
                provideUpdatedText();
            }
            @Override
            public void removeUpdate(DocumentEvent arg0) {
                provideUpdatedText();
            }
        });
        setSize(512, 128);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().displayGUI());
    }

}