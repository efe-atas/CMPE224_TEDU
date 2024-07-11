//----------------------------------------------------
// Title: Valuefinder Class
// Author: İsmail Efe Ataş
// ID: 
// Section: 1
// Assignment: Homework2_Question_1
// Description:  Class for processing values from file lines
//-----------------------------------------------------

import java.util.List;

public class Valuefinder {
    public static int V;
    public static int E;

    static int count = 0;

    //--------------------------------------------------------
    // Summary: Processes each line from the file to extract
    // vertices and edges information and adds edges to the graph.
    // Precondition: graph is an instance of DirectGraph and lines
    // is a list of strings
    // Postcondition: Graph edges are added based on file content.
    //--------------------------------------------------------
    public static void processLines(DirectGraph graph, List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split(" ");
            if(count == 0){
                V = Integer.parseInt(parts[0]);
                count++;
            } else if(count == 1){
                E = Integer.parseInt(parts[0]);
                count++;
            }

            if (parts.length == 2) {
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
