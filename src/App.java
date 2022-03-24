
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {

    public static void print(String msg) {
        System.out.println(msg);
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/simple-dictionary.txt"));
        Trie trie = new Trie();
        ArrayList<String> words = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            try {
                String currentWord = scanner.nextLine();
                // print("'" + currentWord + "'");
                words.add(currentWord.toLowerCase());
                trie.insert(currentWord.toLowerCase());    
            } catch (NullPointerException e) {
                // seems to be getting stuck at capital words 
                e.printStackTrace();
            }
        }
        print(trie.toString());
        // trie.insert("monke");
        // print(Boolean.toString(trie.search("wound 1")));
        // print("Successfully added all words.");
        // for (int i = 0; i < words.size(); i++) 
            // System.out.println("'" + words.get(i) + "'");
            // System.out.println(words.get(i) + " " + Boolean.toString(trie.search(words.get(i).strip())));
        // print("Searching for words:");
        // print(Boolean.toString(trie.search("warmth")));
        // ArrayList<String> monke = trie.possibleWords("trans");
        // for (int i = 0; i < monke.size(); i++) {
        //     print(monke.get(i));
        // }
    }
}
