//-----------------------------------------------------
// Title: Graph Class
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: Homework_1 Question_1
// Description: This class manages a graph representation using adjacency lists.
//-----------------------------------------------------

import java.util.ArrayList;

public class Graph {
    public final int V; // Total number of vertices in the graph
    private ArrayList<ArrayList<Integer>> adj; // Adjacency lists for each vertex

    //--------------------------------------------------------
    // Summary: Constructor that initializes an empty graph with V vertices.
    // Precondition: V is a positive integer.
    // Postcondition: A graph with V vertices and no edges is created.
    //--------------------------------------------------------
    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<ArrayList<Integer>>(V);
        for (int v = 0; v < V; v++) {
            adj.add(new ArrayList<Integer>());
        }
    }

    //--------------------------------------------------------
    // Summary: Adds an undirected edge between vertices v and w.
    // Precondition: v and w are valid indices of vertices within the graph.
    // Postcondition: An edge between v and w is added to the graph.
    //--------------------------------------------------------
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    //--------------------------------------------------------
    // Summary: Returns an iterable of vertices adjacent to vertex v.
    // Precondition: v is a valid index of a vertex within the graph.
    // Postcondition: Iterable object of vertices adjacent to v is returned.
    //--------------------------------------------------------
    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }

}
