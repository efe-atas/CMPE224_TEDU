//-----------------------------------------------------
// Title: BreadthFirstPaths Class
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: Homework_1 Question_1
// Description: This class implements the breadth-first search algorithm to find shortest paths in a graph.
//-----------------------------------------------------
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class BreadthFirstPaths {
    private boolean[] marked;  // Marks vertices that have been visited
    private int[] edgeTo;      // Last vertex on known path to this vertex
    private int[] distTo;      // Number of edges shortest known path to this vertex

    private int time_state;    // State time for controlling path traversal
    private int time_distance; // Distance time for adding to result when ready

    private int result = 0;    // Result of the computation

    private boolean on_road = true;   // Status flag indicating if on road
    private boolean ready_go = true;  // Status flag indicating if ready to go

    //--------------------------------------------------------
    // Summary: Constructor initializes data structures and performs BFS
    // Precondition: G is a graph object, X is the starting vertex,
    //               time_state and time_distance are integers.
    // Postcondition: BFS is performed starting from vertex X.
    //--------------------------------------------------------
    public BreadthFirstPaths(Graph G, int X, int time_state, int time_distance) {
        this.time_state = time_state;
        this.time_distance = time_distance;
        marked = new boolean[G.V]; 
        edgeTo = new int[G.V];
        distTo = new int[G.V];
        Arrays.fill(distTo, Integer.MAX_VALUE); 
        bfs(G, X, time_state, time_distance);
    }

    //--------------------------------------------------------
    // Summary: Performs breadth-first search to find shortest paths from source
    // Precondition: G is the graph, X is the source vertex,
    //               time_state and time_distance are used for controlling path update
    // Postcondition: Shortest paths are calculated from source X
    //--------------------------------------------------------
    public void bfs(Graph G, int X, int time_state, int time_distance) {
        Queue<Integer> q = new LinkedList<>(); 
        q.add(X);
        marked[X] = true;
        distTo[X] = 0;
        while (!q.isEmpty()) {
            int v = q.remove(); 
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.add(w); 
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    //--------------------------------------------------------
    // Summary: Prints the path from X to Y if exists, else prints no path
    // Precondition: X is the source, Y is the destination
    // Postcondition: Path from X to Y is printed if exists, and total time cost is displayed
    //--------------------------------------------------------
    public void printRoad(int X, int Y) {
        if (!marked[Y]) {
            System.out.println("Yol yok");
            return;
        }
        LinkedList<Integer> path = new LinkedList<>();
        for (int x = Y; x != X;) {
            if(result % time_state == 0){
                ready_go = true;
            }
            else{
                ready_go = false;
            }

            if(ready_go){
                path.addFirst(x);
                x = edgeTo[x];
                result += time_distance;
            }
            else {
                result++;
            }
        }
        path.addFirst(X);
        System.out.println(path.size());
        for (int x : path) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println(result);
    }
}
