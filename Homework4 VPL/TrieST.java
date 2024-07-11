//-----------------------------------------------------
// Title: TrieST
// Author: İsmail Efe Ataş
// ID: 
// Section: 1
// Assignment: 4
// Description: This TrieST class is a data structure that stores key-value pairs and provides efficient search, auto-complete, and pattern matching functions.
//-----------------------------------------------------
import java.util.*;
public class TrieST<Value> {
    private static final int R = 256; // Total number of possible characters (Extended ASCII)
    private Node root; // Root of the Trie
    private int n; // Number of keys in the Trie
    private Map<String, Integer> frequencyMap = new HashMap<>(); // Map to keep track of key frequencies

    private static class Node {
        private Object val; // Value associated with the key
        private Node[] next = new Node[R]; // Array of next nodes
    }

    // Constructor for initializing the TrieST
    public TrieST() {
    }

    // Summary: Retrieves the value associated with the given key
    // Precondition: key is a non-null String
    // Postcondition: Returns the value associated with the key, or null if the key is not present
    public Value get(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    // Summary: Checks if the given key is present in the Trie
    // Precondition: arg is a non-null String
    // Postcondition: Returns true if the key is present, false otherwise
    public boolean search(String arg) {
        if (arg == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(arg) != null;
    }

    // Summary: Retrieves the node corresponding to the given key
    // Precondition: x is the current node, key is the string key, d is the current depth
    // Postcondition: Returns the node associated with the key, or null if the key is not present
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    // Summary: Inserts the key-value pair into the Trie
    // Precondition: key is a non-null String, val is the value to be associated with the key
    // Postcondition: The key-value pair is added to the Trie, and the frequency map is updated
    public void put(String key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        root = put(root, key, val, 0);
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
    }

    // Summary: Helper method to insert the key-value pair into the Trie
    // Precondition: x is the current node, key is the string key, val is the value to be associated, d is the current depth
    // Postcondition: Returns the updated node after inserting the key-value pair
    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.val == null) n++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    // Summary: Returns the number of keys in the Trie
    // Precondition: None
    // Postcondition: Returns the number of keys
    public int size() {
        return n;
    }

    // Summary: Checks if the Trie is empty
    // Precondition: None
    // Postcondition: Returns true if the Trie is empty, false otherwise
    public boolean isEmpty() {
        return size() == 0;
    }

    // Summary: Returns all keys in the Trie
    // Precondition: None
    // Postcondition: Returns an iterable collection of all keys
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    // Summary: Prints the keys with the given prefix in lexicographical order
    // Precondition: prefix is a non-null String
    // Postcondition: Prints the keys with the given prefix
    public void autoComplete(String prefix) {
        ArrayList<String> prefixsort = new ArrayList<>();
        for (String s : keysWithPrefix(prefix))
            prefixsort.add(s);

        prefixsort.sort(String::compareTo);

        if (prefixsort.isEmpty()) {
            System.out.println("No words");
        } else {
            System.out.println(String.join(", ", prefixsort));
        }
    }

    // Summary: Returns all keys with the given prefix
    // Precondition: prefix is a non-null String
    // Postcondition: Returns an iterable collection of keys with the given prefix
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new LinkedList<>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    // Summary: Helper method to collect all keys with the given prefix
    // Precondition: x is the current node, prefix is the current prefix, results is a queue to store results
    // Postcondition: The results queue contains all keys with the given prefix
    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.val != null) results.add(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // Summary: Prints the keys with the given prefix and suffix in lexicographical order
    // Precondition: prefix and suffix are non-null Strings
    // Postcondition: Prints the keys with the given prefix and suffix
    public void fullAutoComplete(String prefix, String suffix) {
        Node x = get(root, prefix, 0);
        if (x == null) {
            System.out.println("No words");
            return;
        }
        ArrayList<String> results = new ArrayList<>();
        collectWithSuffix(x, new StringBuilder(prefix), results, suffix);
        results.sort(String::compareTo);
        if (results.isEmpty()) {
            System.out.println("No words");
        } else {
            System.out.println(String.join(", ", results));
        }
    }

    // Summary: Helper method to collect all keys with the given prefix and suffix
    // Precondition: x is the current node, prefix is the current prefix, results is a list to store results, suffix is the suffix to match
    // Postcondition: The results list contains all keys with the given prefix and suffix
    private void collectWithSuffix(Node x, StringBuilder prefix, ArrayList<String> results, String suffix) {
        if (x == null) return;
        if (x.val != null && prefix.toString().endsWith(suffix)) results.add(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collectWithSuffix(x.next[c], prefix, results, suffix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // Summary: Prints the keys with the given suffix in lexicographical order
    // Precondition: suffix is a non-null String
    // Postcondition: Prints the keys with the given suffix
    public void reverseAutoComplete(String suffix) {
        ArrayList<String> results = new ArrayList<>();
        collectReverse(root, new StringBuilder(), results, suffix);
        results.sort(String::compareTo);
        if (results.isEmpty()) {
            System.out.println("No words");
        } else {
            System.out.println(String.join(", ", results));
        }
    }

    // Summary: Helper method to collect all keys with the given suffix
    // Precondition: x is the current node, prefix is the current prefix, results is a list to store results, suffix is the suffix to match
    // Postcondition: The results list contains all keys with the given suffix
    private void collectReverse(Node x, StringBuilder prefix, ArrayList<String> results, String suffix) {
        if (x == null) return;
        if (x.val != null && prefix.toString().endsWith(suffix)) results.add(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collectReverse(x.next[c], prefix, results, suffix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // Summary: Prints the top K most frequent keys in lexicographical order
    // Precondition: k is a positive integer
    // Postcondition: Prints the top K most frequent keys
    public void findTopK(int k) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        sortedEntries.sort((a, b) -> {
            int freqCompare = b.getValue().compareTo(a.getValue());
            if (freqCompare != 0) {
                return freqCompare;
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        });

        List<String> topKWords = new ArrayList<>();
        for (int i = 0; i < k && i < sortedEntries.size(); i++) {
            topKWords.add(sortedEntries.get(i).getKey());
        }

        topKWords.sort(String::compareTo);

        if (topKWords.isEmpty()) {
            System.out.println("No words");
        } else {
            System.out.println(String.join(", ", topKWords));
        }
    }

    // Summary: Returns all keys that match the given pattern
    // Precondition: pattern is a non-null String containing '.' as a wildcard character
    // Postcondition: Returns an iterable collection of keys that match the pattern
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new LinkedList<>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    // Summary: Helper method to collect all keys that match the given pattern
    // Precondition: x is the current node, prefix is the current prefix, pattern is the pattern to match, results is a queue to store results
    // Postcondition: The results queue contains all keys that match the pattern
    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.val != null)
            results.add(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
