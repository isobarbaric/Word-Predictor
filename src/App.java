
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
            String currentWord = scanner.nextLine();
            words.add(currentWord);
            trie.insert(currentWord);    
        }
        print("Successfully added all words.");
        ArrayList<String> rn = trie.possibleWords("you");
        for (int i = 0; i < rn.size(); i++) {
            System.out.println(rn.get(i));
        }
    }
}