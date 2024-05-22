//-----------------------------------------------------
// Title: Main
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: 4
// Description: The Main class processes a file and performs Trie operations based on user input.
//-----------------------------------------------------
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int topk = 0; // Variable to store the value of top K elements
        Scanner keyboard = new Scanner(System.in); // Scanner object to read input from the keyboard
        String filename = keyboard.nextLine(); // Read the filename from the user input
        String operation = keyboard.next(); // Read the operation type from the user input
        String argument = ""; // Variable to store the first argument for operations
        String argument_2 = " "; // Variable to store the second argument for 'full' operation

        // Check the operation type and read additional arguments if needed
        if (operation.equals("topk")) {
            topk = keyboard.nextInt(); // Read the number of top K elements for the 'topk' operation
        } else {
            argument = keyboard.next(); // Read the first argument for other operations
            if (operation.equals("full")) {
                argument_2 = keyboard.next(); // Read the second argument for the 'full' operation
            }
        }

        TrieST<Integer> st = new TrieST<>(); // Create a new TrieST object

        ArrayList<String> words = new ArrayList<>(); // List to store words from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(" "); // Split each line into words
                for (String x : array) {
                    // Remove punctuation and convert to lowercase
                    x = x.replaceAll("\\p{Punct}", "").toLowerCase();
                    if (!x.isEmpty()) {
                        words.add(x); // Add the cleaned word to the list
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage()); // Handle file reading errors
        }

        int i = 0; // Counter for assigning values to words in the Trie
        for (String word : words) {
            st.put(word, i++); // Insert each word into the Trie with a unique value
        }

        // Perform the operation based on the user's input
        switch (operation) {
            case "search":
                System.out.println(st.search(argument.toLowerCase())); // Search for the argument in the Trie
                break;
            case "autocomplete":
                st.autoComplete(argument.toLowerCase()); // Perform autocomplete for the argument
                break;
            case "reverse":
                st.reverseAutoComplete(argument.toLowerCase()); // Perform reverse autocomplete for the argument
                break;
            case "full":
                st.fullAutoComplete(argument.toLowerCase(), argument_2.toLowerCase()); // Perform full autocomplete
                break;
            case "topk":
                st.findTopK(topk); // Find and print the top K frequent words
                break;
            default:
                System.out.println("There is no correct operation"); // Handle invalid operations
                break;
        }
    }
}
