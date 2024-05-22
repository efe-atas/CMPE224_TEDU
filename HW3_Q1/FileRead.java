//-----------------------------------------------------
// Title: FileRead
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: 3
// Description: Reads lines from a file whose path is given.
//-----------------------------------------------------
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {
    //--------------------------------------------------------
    // Summary: Reads lines from a file whose path is given.
    // Precondition: filePath is a String representing the path to the file.
    // Postcondition: Returns an ArrayList of Strings, where each element
    // represents a line read from the file. If an error occurs during
    // reading, it prints an error message.
    //--------------------------------------------------------
    public static ArrayList<String> readLines(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }
}
