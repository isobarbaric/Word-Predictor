
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Trie {
    
    private HashMap<Character, Node> parentNodes = new HashMap<Character, Node>();
    private ArrayList<Node> graph = new ArrayList<Node>();
    static int nodeIDCounter = 0;

    public class Node {
        HashMap<Character, Node> children = new HashMap<Character, Node>();
        char identity;
        int nodeID = -1;
        boolean wordOver = false;
        Node(char identity) {
            nodeID = nodeIDCounter;
            graph.add(this);
            nodeIDCounter++;
            this.identity = identity;
        }
        public void add(char newChild) {
            if (children.containsKey(newChild)) return;
            children.put(newChild, new Node(newChild));
            graph.set(nodeID, this);
        }
    }   

    public void insert(String wordToAdd) {
        int previousNodeIndex = parentNodes.get(wordToAdd.charAt(0)).nodeID; 
        String checker = "";
        checker += graph.get(previousNodeIndex).identity;
        for (int i = 1; i < wordToAdd.length(); i++) {
            if (!graph.get(previousNodeIndex).children.containsKey(wordToAdd.charAt(i))) 
                graph.get(previousNodeIndex).add(wordToAdd.charAt(i));
            previousNodeIndex = graph.get(previousNodeIndex).children.get(wordToAdd.charAt(i)).nodeID;
            checker += graph.get(previousNodeIndex).identity;
        }
        assert(checker.equals(wordToAdd));
        graph.get(previousNodeIndex).wordOver = true;
    }

    public boolean search(String wordToSearchFor) {
        int previousNodeIndex = parentNodes.get(wordToSearchFor.charAt(0)).nodeID;
        String checker = "";
        checker += graph.get(previousNodeIndex).identity;
        for (int i = 1; i < wordToSearchFor.length(); i++) {
            if (!graph.get(previousNodeIndex).children.containsKey(wordToSearchFor.charAt(i))) {
                return false;
            }
            System.out.println(graph.get(previousNodeIndex).identity);
            previousNodeIndex = graph.get(previousNodeIndex).children.get(wordToSearchFor.charAt(i)).nodeID;
            checker += graph.get(previousNodeIndex).identity;
        }
        assert(checker.equals(wordToSearchFor));
        return true;
    }

    public String possibleWords(String prefix) {
        ArrayList<String> listWords = new ArrayList<String>();
        int lastcharNodeIndex = parentNodes.get(prefix.charAt(0)).nodeID;
        for (int i = 1; i < prefix.length(); i++) {
            if (!graph.get(lastcharNodeIndex).children.containsKey(prefix.charAt(i))) 
                return stringifiedWordList(listWords);
            lastcharNodeIndex = graph.get(lastcharNodeIndex).children.get(prefix.charAt(i)).nodeID;
        }
        boolean visited[] = new boolean[nodeIDCounter];
        for (int i = 0; i < visited.length; i++) visited[i] = false;
        listWords = DepthFirstSearch(prefix, lastcharNodeIndex, visited);
        Collections.sort(listWords, (a, b) -> Integer.compare(a.length(), b.length()));
        return stringifiedWordList(listWords);
    }

    String stringifiedWordList(ArrayList<String> words) {
        String representation = "<html>";
        for (int i = 0; i < words.size(); i++) 
            representation += words.get(i) + "<br>";
        return representation;
    }

    public ArrayList<String> DepthFirstSearch(String currentString, int currentNodeIndex, boolean visited[]) {
        ArrayList<String> allWords = new ArrayList<String>();
        if (visited[currentNodeIndex]) return allWords;
        visited[currentNodeIndex] = true;
        if (graph.get(currentNodeIndex).wordOver) 
            allWords.add(currentString);
        for (char letter : graph.get(currentNodeIndex).children.keySet())
            allWords.addAll(DepthFirstSearch(currentString + letter, graph.get(currentNodeIndex).children.get(letter).nodeID, visited));
        return allWords;
    } 

    @Override
    public String toString() {
        String toDisplay = "";
        ArrayList<String> allWords = new ArrayList<String>();
        boolean visited[] = new boolean[nodeIDCounter];
        for (int i = 0; i < visited.length; i++) visited[i] = false;
        for (char letter: parentNodes.keySet())
            allWords.addAll(DepthFirstSearch(Character.toString(letter), parentNodes.get(letter).nodeID, visited));
        for (String word: allWords) 
            toDisplay += word + ", ";
        return toDisplay;
    }   

    Trie() {
        for (char letter = 'a'; letter <= 'z'; letter++) 
            parentNodes.put(letter, new Node(letter));
        for (char letter = 'A'; letter <= 'Z'; letter++) 
            parentNodes.put(letter, new Node(letter));
    }
}