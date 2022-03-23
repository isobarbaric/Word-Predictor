
import java.util.HashMap;

public class Trie {
    public class Node {
        // static int numNodes = 0;
        Character identity;
        HashMap<Node, Node> children;
        boolean wordOver = false;
        Node(Character identity) {
            this.identity = identity;
            // numNodes++;
        }
        public void addChild(Character newChild) {
            // children[new Node(newChild)];
        }
    }   
    public void add(String wordToAdd) {
        Node currentNode = new Node(wordToAdd.charAt(0));
        for (int i = 1; i < wordToAdd.length(); i++) {
            int location = exists(wordToAdd.charAt(i), wordToAdd);
            // if (location == -1) 

            // currentNode = 
        }
    }
    // int exists(Character toFind, ArrayList<Node> container) {
    //     for (int i = 0; i < container.length; i++) {

    //     }
    //     return -1;
    // }
    public void search() {
        dfs();
    }
    // void dfs() {
    //     boolean vis[]  = new boolean[numNodes];
    // }
    public static void main(String[] args) {
        
    }
}