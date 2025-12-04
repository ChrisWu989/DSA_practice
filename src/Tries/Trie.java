package Tries;
//check wheter words are present in the trie or not
// partial strings
//insert delete search
//delete search are booleans 

//problem 41
public class Trie {
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserting a word into Trie
    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a'; // ASCII representation to get index 
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEndOfWord = true;
    }

    // Searching for a word in the tree
    public boolean search(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return false;
            node = node.children[idx];
        }

        return node.isEndOfWord;
    }

    // delete a word from the Trie
    public void delete(String word) {
        deleteHelper(root, word, 0); // utilize helper becuase recursion
    }

    // returns true if the current node can be safely deleted
    private boolean deleteHelper(TrieNode node, String word, int index) {
        if (index == word.length()) {
            if (!node.isEndOfWord) return false;  // word not found
            node.isEndOfWord = false;

            return isEmpty(node); // delete node if no children
        }

        char c = word.charAt(index);
        int idx = c - 'a';
        TrieNode child = node.children[idx];

        if (child == null) return false; // word not found

        boolean shouldDeleteChild = deleteHelper(child, word, index + 1);

        if (shouldDeleteChild) {
            node.children[idx] = null;  // remove the pointer

            return !node.isEndOfWord && isEmpty(node);
        }

        return false;
    }

    // Helper: check if node has no children
    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) return false;
        }
        return true;
    }

    // MAIN to test
    public static void main(String[] args) {
        Trie t = new Trie();

        t.insert("apple");
        t.insert("app");
        t.insert("bat");
        t.insert("batt");

        System.out.println(t.search("apple"));   // true
        System.out.println(t.search("app"));     // true
        System.out.println(t.search("appl"));    // false

        t.delete("bat");
        System.out.println(t.search("bat"));     // false
        System.out.println(t.search("batt"));    // true

        t.delete("batt");
        System.out.println(t.search("batt"));    // false
    }
}
