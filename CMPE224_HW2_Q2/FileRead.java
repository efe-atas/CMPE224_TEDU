//-----------------------------------------------------
// Title: FileRead Class
// Author: İsmail Efe Ataş
// ID: 
// Section: 1
// Assignment: Homework2_Question_2
// Description:  Class for reading lines from a file.
//-----------------------------------------------------
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileRead {
    //--------------------------------------------------------
    // Summary: Reads all lines from the specified file and returns them as a list of strings.
    // Precondition: filePath is a string that specifies the path to the file.
    // Postcondition: Returns a list containing each line of the file as a string.
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
