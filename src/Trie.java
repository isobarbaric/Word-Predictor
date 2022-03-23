
import java.util.HashMap;
import java.util.ArrayList;

public class Trie {
    
    HashMap<Character, Node> parentNodes = new HashMap<Character, Node>();
    ArrayList<Node> graph = new ArrayList<Node>();
    static int nodeIDCounter = 0;

    public class Node {
        HashMap<Character, Node> children = new HashMap<Character, Node>();
        int nodeID = -1;
        Node() {
            nodeID = nodeIDCounter;
            graph.add(this);
            nodeIDCounter++;
        }
        public void add(Character newChild) {
            if (children.containsKey(newChild)) return;
            children.put(newChild, new Node());
            graph.set(nodeID, this);
        }
    }   

    public void add(String wordToAdd) {
        int previousNodeIndex = parentNodes.get(wordToAdd.charAt(0)).nodeID; 
        for (int i = 1; i < wordToAdd.length(); i++) {
            if (!graph.get(previousNodeIndex).children.containsKey(wordToAdd.charAt(i))) {
                graph.get(previousNodeIndex).add(wordToAdd.charAt(i));
            }
            previousNodeIndex = graph.get(previousNodeIndex).children.get(wordToAdd.charAt(i)).nodeID;
        }
    }

    public boolean search(String wordToSearchFor) {
        int currentNodeIndex = parentNodes.get(wordToSearchFor.charAt(0)).nodeID;
        for (int i = 1; i < wordToSearchFor.length(); i++) {
            if (!graph.get(currentNodeIndex).children.containsKey(wordToSearchFor.charAt(i))) return false;
            currentNodeIndex = graph.get(currentNodeIndex).children.get(wordToSearchFor.charAt(i)).nodeID;
        }
        return true;
    }

    Trie() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            parentNodes.put(letter, new Node());
            graph.add(parentNodes.get(letter));
        }

    }
}