import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main {

    private final static String toDisplay = "Enter a prefix to see possible words in the dictionary with that prefix.";

    private static String entered;
 
    private static Trie trie = new Trie();
    
    public static class GUI extends JFrame {

        private JFrame frame;
        private JPanel panel;
        private JTextField textField;
        private JLabel label, listOfWords;
        private Font fontToBeUsed = new Font("Arial", Font.BOLD, 12);
    
        public GUI(String name, String label) {
            frame = new JFrame(name);
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            textField = new JTextField();
            textField.setSize(512, 32);
            textField.setFont(fontToBeUsed);
            this.label = new JLabel(label);
            this.label.setFont(fontToBeUsed);
            listOfWords = new JLabel();
        }
    
        public String obtainPrefixWords(String text) {
            if (text.length() == 0) return "";
            return trie.possibleWords(text);
        }
    
        void displayGUI() {
            panel.add(textField);
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            getContentPane().add(textField, BorderLayout.CENTER);
            getContentPane().add(label, BorderLayout.NORTH);
            getContentPane().add(listOfWords, BorderLayout.SOUTH);
            DocumentListener wordFeeder = new DocumentListener() {
                private void provideRelatedWords() {
                    listOfWords.setText(obtainPrefixWords(textField.getText()));
                }
                @Override
                public void changedUpdate(DocumentEvent arg0) {
                    provideRelatedWords();
                }
                @Override 
                public void insertUpdate(DocumentEvent arg0) {
                    provideRelatedWords();
                }
                @Override
                public void removeUpdate(DocumentEvent arg0) {
                    provideRelatedWords();
                }                
            };
            textField.getDocument().addDocumentListener(wordFeeder);
            setSize(512, 128);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }
    
        public void main() {
            SwingUtilities.invokeLater(() -> new GUI("Word Predictor", toDisplay).displayGUI());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        entered = new String();
        Scanner scanner = new Scanner(new File("src/simple-dictionary.txt"));
        ArrayList<String> words = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            String currentWord = scanner.nextLine();
            words.add(currentWord);
            trie.insert(currentWord);    
        }
        GUI promptWindow = new GUI("Word Predictor", toDisplay);
        promptWindow.main();
    }
}