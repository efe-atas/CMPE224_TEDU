//----------------------------------------------------
// Title: FileRead Class
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: Homework2_Question_1
// Description: Class for reading file content into a list of strings
//-----------------------------------------------------
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
    //--------------------------------------------------------
    // Summary: Reads all lines from a file at the given file path.
    // Precondition: filePath is a string representing the file path
    // Postcondition: Returns a list of strings, each representing a line from the file.
    //--------------------------------------------------------
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
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