import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
// import java.util.Collections;
// import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
// import javax.swing.JButton;

public class Main {

    final static String toDisplay = "Enter a prefix to see possible words in the dictionary with that prefix.\nTo quit the program, press the exit button in the top right corner.";

    static String entered;

    static Trie trie;

    public static class GUI extends JFrame {

        private JFrame frame;
        private JPanel panel;
        private JTextField textField;
        private JLabel label, listOfWords;
    
        public GUI(String name, String label) {
            frame = new JFrame(name);
            panel = new JPanel();
            textField = new JTextField("", 20);
            this.label = new JLabel(label);
            listOfWords = new JLabel();
        }
    
        public String obtainPrefixWords(String text) {
            return trie.possibleWords(text);
        }
    
        void displayGUI() {
            panel.add(textField);
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            getContentPane().add(textField, BorderLayout.CENTER);
            getContentPane().add(label, BorderLayout.SOUTH);
            getContentPane().add(listOfWords, BorderLayout.SOUTH);
            textField.getDocument().addDocumentListener(new DocumentListener() {
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
            });
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
        // showUserPossibleWords(trie);
    }
}