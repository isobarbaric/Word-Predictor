public class App {

    public static void print(String msg) {
        System.out.println(msg);
    }
    public static void main(String[] args) throws Exception {
        Trie newTrie = new Trie();
        newTrie.addWord("monke");
        print(Boolean.toString(newTrie.searchWord("pog")));
    }
}
