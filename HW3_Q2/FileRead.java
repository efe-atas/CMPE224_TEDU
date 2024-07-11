//-----------------------------------------------------
// Title: FileRead
// Author: İsmail Efe Ataş
// ID: 
// Section: 1
// Assignment: 3
// Description: Reads a graph from a file and returns it as an adjacency matrix.
//-----------------------------------------------------

import java.io.*;
import java.util.*;

public class FileRead {

    //--------------------------------------------------------
    // Summary: Reads a graph from a file and returns it as an adjacency matrix.
    // Precondition: filename is a String representing the path to the file. 
    // The file must contain the number of vertices (V) in the first line, 
    // the number of edges (E) in the second line, and each subsequent line 
    // represents an edge with two vertices (u and v) and a weight (w).
    // Postcondition: Returns a 2D array (adjacency matrix) representing the graph.
    //--------------------------------------------------------
    public int[][] readGraph(String filename) {
        int[][] graph = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int V = Integer.parseInt(br.readLine().trim());
            int E = Integer.parseInt(br.readLine().trim());
            graph = new int[V][V];

            for (int i = 0; i < E; i++) {
                String[] parts = br.readLine().trim().split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int w = Integer.parseInt(parts[2]);
                graph[u][v] = w;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }
}
