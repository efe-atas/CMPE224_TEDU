//-----------------------------------------------------
// Title: ValueFinder Class
// Author: İsmail Efe Ataş
// ID: 
// Section: 1
// Assignment: Homework2_Question_2
// Description: Class for processing file lines to extract graph data.
//-----------------------------------------------------
import java.util.List;


public class ValueFinder {

    public static int V;  // Number of vertices
    public static int E;  // Number of edges

    static int count = 0; // Helper counter to track the initialization stages

    //--------------------------------------------------------
    // Summary: Processes lines from the file to extract vertex and edge information
    // and adds edges to the graph based on the lines read.
    // Precondition: graph is an instance of DirectGraph, lines is a list of strings,
    // each representing a line from the file.
    // Postcondition: Graph edges are added, V and E are initialized based on file content.
    //--------------------------------------------------------
    public static void processLines(DirectGraph graph, List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split(" ");
            if (count == 0) {
                V = Integer.parseInt(parts[0]); // First line: number of vertices
                count++;
            } else if (count == 1) {
                E = Integer.parseInt(parts[0]); // Second line: number of edges
                count++;
            }

            if (parts.length == 2) { // Line with two parts: source and destination for an edge
                try {
                    int source = Integer.parseInt(parts[0]);
                    int destination = Integer.parseInt(parts[1]);
                    graph.addEdge(source, destination);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            }
        }
    }
}
