package wordpredictor;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Trie {

    // attributes of a Trie object
    private final HashMap<Character, Node> parentNodes;
    private final ArrayList<Node> graph;
    private static int nodeIDCounter;

    // static-block for safe-initialization
    static {
        nodeIDCounter = 0;
    }

    // a nested class to be used in constructing every Trie object
    public class Node {

        // attributes of a Node object
        private HashMap<Character, Node> children = new HashMap<>();
        private char identity;
        private int nodeID = -1;
        private boolean wordOver = false;

        /**
         * Primary constructor
         * 
         * @param identity character associated with the Node object
         */
        public Node(char identity) {
            nodeID = nodeIDCounter;
            graph.add(this);
            nodeIDCounter++;
            this.identity = identity;
        }

        /**
         * Mutator for the
         * 
         * @param newChild
         */
        public void addChild(char newChild) {
            if (children.containsKey(newChild))
                return;
            children.put(newChild, new Node(newChild));
            graph.set(nodeID, this);
        }
    }

    /**
     * Default constructor
     */
    public Trie() {
        parentNodes = new HashMap<>();
        graph = new ArrayList<>();

        // creating parent nodes in parentNodes for the lowercase alphabet
        for (char letter = 'a'; letter <= 'z'; letter++)
            parentNodes.put(letter, new Node(letter));

        // creating parent nodes in parentNodes for the uppercase alphabet
        for (char letter = 'A'; letter <= 'Z'; letter++)
            parentNodes.put(letter, new Node(letter));
    }

    /**
     * A method to allow word insertion into the Trie object
     * 
     * @param wordToAdd
     */
    public void insert(String wordToAdd) {
        int previousNodeIndex = parentNodes.get(wordToAdd.charAt(0)).nodeID;
        String checker = "";
        checker += graph.get(previousNodeIndex).identity;
        for (int i = 1; i < wordToAdd.length(); i++) {
            if (!graph.get(previousNodeIndex).children.containsKey(wordToAdd.charAt(i)))
                graph.get(previousNodeIndex).addChild(wordToAdd.charAt(i));
            previousNodeIndex = graph.get(previousNodeIndex).children.get(wordToAdd.charAt(i)).nodeID;
            checker += graph.get(previousNodeIndex).identity;
        }
        assert (checker.equals(wordToAdd));
        graph.get(previousNodeIndex).wordOver = true;
    }

    /**
     * A method to allow word searches in the Trie object
     * 
     * @param wordToSearchFor the word to be found in the Trie
     * @return whether or not the provided word exists in the Trie
     */
    public boolean search(String wordToSearchFor) {
        int previousNodeIndex = parentNodes.get(wordToSearchFor.charAt(0)).nodeID;
        String checker = "";
        checker += graph.get(previousNodeIndex).identity;
        for (int i = 1; i < wordToSearchFor.length(); i++) {
            if (!graph.get(previousNodeIndex).children.containsKey(wordToSearchFor.charAt(i))) {
                return false;
            }
            previousNodeIndex = graph.get(previousNodeIndex).children.get(wordToSearchFor.charAt(i)).nodeID;
            checker += graph.get(previousNodeIndex).identity;
        }
        assert (checker.equals(wordToSearchFor));
        return true;
    }

    /**
     * A method to return a list of words associated with a prefix of the word provided
     * 
     * @param prefix a string that should form the prefix of any potential words
     *               found in the Trie
     * @return an ArrayList of potential words with the matching prefix in an
     *         ordered manner
     */
    public ArrayList<String> possibleWords(String prefix) {
        ArrayList<String> listWords = new ArrayList<>();
        int lastcharNodeIndex = parentNodes.get(prefix.charAt(0)).nodeID;
        for (int i = 1; i < prefix.length(); i++) {
            if (!graph.get(lastcharNodeIndex).children.containsKey(prefix.charAt(i)))
                return listWords;
            lastcharNodeIndex = graph.get(lastcharNodeIndex).children.get(prefix.charAt(i)).nodeID;
        }
        boolean visited[] = new boolean[nodeIDCounter];
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;
        listWords = depthFirstSearch(prefix, lastcharNodeIndex, visited);
        Collections.sort(listWords, (a, b) -> Integer.compare(a.length(), b.length()));
        return listWords;
    }

    /**
     * A method to traverse down the Trie (using DFS) and provide appropriate results according to the string provided
     * 
     * @param currentString    a string representing
     * @param currentNodeIndex the nodeID of the current Node object being explored
     * @param visited          the boolean array assisting with DFS
     * @return an ArrayList of words with the matching prefix in no specific order
     */
    private ArrayList<String> depthFirstSearch(String currentString, int currentNodeIndex, boolean visited[]) {
        ArrayList<String> allWords = new ArrayList<>();
        if (visited[currentNodeIndex])
            return allWords;
        visited[currentNodeIndex] = true;
        if (graph.get(currentNodeIndex).wordOver)
            allWords.add(currentString);
        for (char letter : graph.get(currentNodeIndex).children.keySet())
            allWords.addAll(depthFirstSearch(currentString + letter,
                    graph.get(currentNodeIndex).children.get(letter).nodeID, visited));
        return allWords;
    }

    /**
     * Standard Java toString method
     * 
     * @return a String containing all of the words inside this Trie object
     */
    @Override
    public String toString() {
        String toDisplay = "";
        ArrayList<String> allWords = new ArrayList<>();
        boolean visited[] = new boolean[nodeIDCounter];
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;
        for (char letter : parentNodes.keySet())
            allWords.addAll(depthFirstSearch(Character.toString(letter), parentNodes.get(letter).nodeID, visited));
        Collections.sort(allWords, String.CASE_INSENSITIVE_ORDER);
        for (String word : allWords)
            toDisplay += word + ", ";
        return toDisplay;
    }

}
