import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Main {

    final static String toDisplay = "Enter a prefix to see possible words in the dictionary with that prefix.\nTo quit the program, press 'q'.";

    /**
     * Takes input from the user using JOptionPane whilst displaying a particular message
     * @param text - the message to be displayed to the user
     */
    public static String input(String text) {
        return (String) JOptionPane.showInputDialog(null, text, "Word Predictor", JOptionPane.INFORMATION_MESSAGE, null, null, "");  
    }
 
    /**
     * Displays a particular message to the user 
     * @param text - the message to be displayed to the user
     */
    public static void display(String text) {
        JOptionPane.showMessageDialog(null, text, "Word Predictor", JOptionPane.INFORMATION_MESSAGE, null);
    }

    public static void showUserPossibleWords(Trie currentTrie) {
        String userResponse = JOptionPane.showInputDialog(toDisplay);
        if (userResponse.equals("q")) return;
        else {
            ArrayList<String> wordsToDisplay = currentTrie.possibleWords(userResponse);
            Collections.sort(wordsToDisplay, (a, b) -> Integer.compare(a.length(), b.length()));;
            String listOfWords = "";
            for (String word: wordsToDisplay) 
                listOfWords += word + '\n';
            display(listOfWords);
            showUserPossibleWords(currentTrie);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/simple-dictionary.txt"));
        Trie trie = new Trie();
        ArrayList<String> words = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            String currentWord = scanner.nextLine();
            words.add(currentWord);
            trie.insert(currentWord);    
        }
        showUserPossibleWords(trie);
    }
}