//-----------------------------------------------------
// Title: Valuefinder
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: 3
// Description: Finds the shortest path from a source vertex to a target vertex in a given graph using Dijkstra's algorithm.
//-----------------------------------------------------
import java.util.*;

public class Valuefinder {

    //--------------------------------------------------------
    // Summary: Finds the shortest path from a source vertex to a target 
    // vertex in a given graph using Dijkstra's algorithm.
    // Precondition: graph is a 2D array representing the adjacency matrix 
    // of the graph, src and target are integers representing the source 
    // and target vertices.
    // Postcondition: Returns a list of integers representing the vertices 
    // in the shortest path from src to target.
    //--------------------------------------------------------
    public List<Integer> findShortestPath(int[][] graph, int src, int target) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        return getPath(parent, src, target);
    }

    //--------------------------------------------------------
    // Summary: Finds the vertex with the minimum distance value that 
    // hasn't been included in the shortest path tree (SPT).
    // Precondition: dist is an array of integers representing the 
    // distance from the source to each vertex, sptSet is a boolean array 
    // indicating whether a vertex is included in the SPT.
    // Postcondition: Returns the index of the vertex with the minimum 
    // distance value.
    //--------------------------------------------------------
    private int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    //--------------------------------------------------------
    // Summary: Constructs the shortest path from the source vertex to 
    // the target vertex using the parent array.
    // Precondition: parent is an array of integers representing the parent 
    // vertices of each vertex in the path, src and target are integers 
    // representing the source and target vertices.
    // Postcondition: Returns a list of integers representing the vertices 
    // in the shortest path from src to target.
    //--------------------------------------------------------
    private List<Integer> getPath(int[] parent, int src, int target) {
        List<Integer> path = new ArrayList<>();
        for (int v = target; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }
}
