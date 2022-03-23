
import java.util.HashMap;
import java.util.ArrayList;

public class Trie {
    
    HashMap<Character, Node> parentNodes = new HashMap<Character, Node>();
    ArrayList<Node> graph = new ArrayList<Node>();
    static int nodeIDCounter = 0;

    public class Node {
        private Character identity;
        private HashMap<Character, Node> children = new HashMap<Character, Node>();
        private int nodeID = -1;
        // boolean wordOver = false; ---> do we need this? just stop when you see you've gotten to a Node with no children I guess
        Node(Character identity) {
            this.identity = identity;
            nodeID = nodeIDCounter;
            graph.add(this);
            nodeIDCounter++;
        }
        public void addChild(Character newChild) {
            if (children.containsKey(newChild)) return;
            children.put(newChild, new Node(newChild));
            graph.set(nodeID, this);
        }
        // getters
        public Character getIdentity() {
            return identity;
        }
        public int getNodeID() {
            return nodeID;
        }
        public HashMap<Character, Node> getChildren() {
            return children;
        }
    }   

    public void addWord(String wordToAdd) {
        int previousNodeIndex = parentNodes.get(wordToAdd.charAt(0)).getNodeID();
        for (int i = 1; i < wordToAdd.length(); i++) {
            if (!graph.get(previousNodeIndex).children.containsKey(wordToAdd.charAt(i))) {
                graph.get(previousNodeIndex).addChild(wordToAdd.charAt(i));
            }
            previousNodeIndex = graph.get(previousNodeIndex).children.get(wordToAdd.charAt(i)).nodeID;
        }
    }

    public boolean searchWord(String wordToSearchFor) {
        int currentNodeIndex = parentNodes.get(wordToSearchFor.charAt(0)).getNodeID();
        for (int i = 1; i < wordToSearchFor.length(); i++) {
            if (!graph.get(currentNodeIndex).children.containsKey(wordToSearchFor.charAt(i))) return false;
            currentNodeIndex = graph.get(currentNodeIndex).children.get(wordToSearchFor.charAt(i)).getNodeID();
        }
        return true;
    }

    Trie() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            parentNodes.put(letter, new Node(letter));
            graph.add(parentNodes.get(letter));
        }

    }
}