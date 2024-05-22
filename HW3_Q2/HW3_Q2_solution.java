//-----------------------------------------------------
// Title: HW3_Q2_solution
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: 3
// Description: Main method to execute the program.
//-----------------------------------------------------
import java.util.*;

public class HW3_Q2_solution {

    //--------------------------------------------------------
    // Summary: Main method to execute the program. It reads a graph from 
    // a file, prints the number of vertices and edges, prints the graph, 
    // finds the shortest paths from vertex 0 to vertices 1, 2, and 3 using 
    // Valuefinder, and prints the paths.
    // Precondition: "HW3_Q2.txt" file exists and has valid format.
    // Postcondition: Prints the graph, the number of edges, and the shortest paths.
    //--------------------------------------------------------
    public static void main(String[] args) {
        FileRead fileRead = new FileRead();
        int[][] graph = fileRead.readGraph("HW3_Q2.txt");

        System.out.println("V=" + graph.length);
        System.out.println("E=" + countEdges(graph));
        printGraph(graph);

        Valuefinder valuefinder = new Valuefinder();

        List<Integer> path1 = valuefinder.findShortestPath(graph, 0, 1);
        List<Integer> path2 = valuefinder.findShortestPath(graph, 0, 2);
        List<Integer> path3 = valuefinder.findShortestPath(graph, 0, 3);

        System.out.println("\nThe result");
        printPath(graph, path1);
        printPath(graph, path2);
        printPath(graph, path3);
    }

    //--------------------------------------------------------
    // Summary: Counts the number of edges in the graph.
    // Precondition: graph is a 2D array representing the adjacency matrix 
    // of the graph.
    // Postcondition: Returns the number of edges in the graph.
    //--------------------------------------------------------
    private static int countEdges(int[][] graph) {
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    //--------------------------------------------------------
    // Summary: Prints the edges of the graph.
    // Precondition: graph is a 2D array representing the adjacency matrix 
    // of the graph.
    // Postcondition: Prints the edges of the graph.
    //--------------------------------------------------------
    private static void printGraph(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    System.out.println(i + " " + j + " " + graph[i][j]);
                }
            }
        }
    }

    //--------------------------------------------------------
    // Summary: Prints the path and total distance.
    // Precondition: graph is a 2D array representing the adjacency matrix 
    // of the graph, path is a list of integers representing the vertices in 
    // the path.
    // Postcondition: Prints the path and the total distance.
    //--------------------------------------------------------
    private static void printPath(int[][] graph, List<Integer> path) {
        if (path.size() < 2) {
            System.out.println("No path");
            return;
        }

        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalDistance += graph[path.get(i)][path.get(i + 1)];
            System.out.print(path.get(i) + " ");
        }
        System.out.println(path.get(path.size() - 1) + " " + totalDistance);
    }
}
