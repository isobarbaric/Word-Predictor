
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
        // while (scanner.hasNextLine()) {
        //     String currentWord = scanner.nextLine();
        //     words.add(currentWord);
        //     trie.insert(currentWord);    
        // }
        
        print(Boolean.toString(trie.search("calle")));
        // print(trie.toString());
        // print(Boolean.toString(trie.search("p")));
        // print(Boolean.toString(trie.search("monke")));
        // boolean visited[] = new boolean[trie.nodeIDCounter];
        // for (int i = 0; i < visited.length; i++) visited[i] = false;
        // trie.DepthFirstSearch("A", 26, visited);
        // trie.insert("chicken");
        // print(trie.toString());
        // print(Boolean.toString(trie.search("monke")));
        // print("Successfully added all words.");
        // for (int i = 0; i < words.size(); i++) 
        //     System.out.println(words.get(i) + " " + Boolean.toString(trie.search(words.get(i).strip())));
        // print("Searching for words:");
        // print(Boolean.toString(trie.search("warmth")));
        // ArrayList<String> monke = trie.possibleWords("trans");
        // for (int i = 0; i < monke.size(); i++) {
        //     print(monke.get(i));
        // }
    }
}