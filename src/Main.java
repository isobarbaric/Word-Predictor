import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main {

    private final static String toDisplay = "Enter a prefix to see possible words in the dictionary with that prefix.";

    private static Trie trie = new Trie();
    
    public static class GUI extends JFrame {

        private JFrame applicationWindow;
        private JPanel topPanel, bottomPanel;
        private JTextField userInputField;
        private JLabel title, wordListDisplay;
        private JScrollPane scrollBar;
        private Font fontToBeUsed = new Font("Arial", Font.BOLD, 12);
    
        public GUI(String name, String title) {
            applicationWindow = new JFrame(name);

            topPanel = new JPanel();
            bottomPanel = new JPanel();
            
            userInputField = new JTextField();
            userInputField.setSize(512, 32);
            userInputField.setFont(fontToBeUsed);
            
            this.title = new JLabel(title);
            this.title.setFont(fontToBeUsed);
            wordListDisplay = new JLabel();

            scrollBar = new JScrollPane(wordListDisplay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }
    
        public String obtainPrefixWords(String text) {
            if (text.length() == 0) return "";
            return trie.possibleWords(text);
        }
    
        void displayGUI() {            
            topPanel.add(userInputField);
            applicationWindow.add(topPanel);

            bottomPanel.add(scrollBar);
            applicationWindow.add(bottomPanel);

            applicationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            applicationWindow.pack();
            applicationWindow.setVisible(true);

            getContentPane().add(userInputField, BorderLayout.CENTER);
            getContentPane().add(title, BorderLayout.NORTH);
            getContentPane().add(wordListDisplay, BorderLayout.SOUTH);
            // frame.add(scrollBar);
            // getContentPane().add(scrollBar, BorderLayout.SOUTH);
            DocumentListener wordFeeder = new DocumentListener() {
                private void provideRelatedWords() {
                    wordListDisplay.setText(obtainPrefixWords(userInputField.getText()));
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
            userInputField.getDocument().addDocumentListener(wordFeeder);
            applicationWindow.setVisible(true);
            applicationWindow.setSize(512, 128);
            applicationWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        public void main() {
            SwingUtilities.invokeLater(() -> new GUI("Word Predictor", toDisplay).displayGUI());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
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