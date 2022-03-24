
import java.util.HashMap;
import java.util.ArrayList;

public class Trie {
    
    private HashMap<Character, Node> parentNodes = new HashMap<Character, Node>();
    private ArrayList<Node> graph = new ArrayList<Node>();
    private static int nodeIDCounter = 0;

    public class Node {
        HashMap<Character, Node> children = new HashMap<Character, Node>();
        int nodeID = -1;
        boolean wordOver = false;
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

    public void insert(String wordToAdd) {
        int previousNodeIndex = parentNodes.get(wordToAdd.charAt(0)).nodeID; 
        for (int i = 1; i < wordToAdd.length(); i++) {
            if (!graph.get(previousNodeIndex).children.containsKey(wordToAdd.charAt(i))) {
                graph.get(previousNodeIndex).add(wordToAdd.charAt(i));
            }
            previousNodeIndex = graph.get(previousNodeIndex).children.get(wordToAdd.charAt(i)).nodeID;
        }
        graph.get(previousNodeIndex).wordOver = true;
    }

    public boolean search(String wordToSearchFor) {
        int currentNodeIndex = parentNodes.get(wordToSearchFor.charAt(0)).nodeID;
        for (int i = 1; i < wordToSearchFor.length(); i++) {
            if (!graph.get(currentNodeIndex).children.containsKey(wordToSearchFor.charAt(i))) return false;
            currentNodeIndex = graph.get(currentNodeIndex).children.get(wordToSearchFor.charAt(i)).nodeID;
        }
        return true;
    }

    public ArrayList<String> possibleWords(String prefix) {
        ArrayList<String> listWords = new ArrayList<String>();
        int lastCharacterNodeIndex = parentNodes.get(prefix.charAt(0)).nodeID;
        for (int i = 1; i < prefix.length(); i++) {
            if (!graph.get(lastCharacterNodeIndex).children.containsKey(prefix.charAt(i))) return listWords;
            lastCharacterNodeIndex = graph.get(lastCharacterNodeIndex).children.get(prefix.charAt(i)).nodeID;
        }
        boolean visited[] = new boolean[nodeIDCounter];
        for (int i = 0; i < visited.length; i++) visited[i] = false;
        listWords = DepthFirstSearch(prefix, lastCharacterNodeIndex, visited);
        return listWords;
    }

    public ArrayList<String> DepthFirstSearch(String currentString, int currentNodeIndex, boolean visited[]) {
        ArrayList<String> allWords = new ArrayList<String>();
        if (visited[currentNodeIndex]) return allWords;
        visited[currentNodeIndex] = true;
        if (graph.get(currentNodeIndex).wordOver) {
            System.out.println(currentString);
            allWords.add(currentString);
        }
        for (Character letter : graph.get(currentNodeIndex).children.keySet())
            allWords.addAll(DepthFirstSearch(currentString + letter, graph.get(currentNodeIndex).children.get(letter).nodeID, visited));
        return allWords;
    } 

    @Override
    public String toString() {
        String toDisplay = "";
        ArrayList<String> allWords = new ArrayList<String>();
        boolean visited[] = new boolean[nodeIDCounter];
        for (int i = 0; i < visited.length; i++) visited[i] = false;
        for (Character letter: parentNodes.keySet())
            allWords.addAll(DepthFirstSearch(letter.toString(), parentNodes.get(letter).nodeID, visited));
        for (String word: allWords) 
            toDisplay += word + '\n';
        return toDisplay;
    }

    Trie() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            parentNodes.put(letter, new Node());
            graph.add(parentNodes.get(letter));
        }
    }
}