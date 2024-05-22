//-----------------------------------------------------
// Title: Main Class
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: Homework1_Question_1
// Description: This class is the main entry point for the program, which reads inputs and processes them using the Graph and BreadthFirstPaths classes.
//-----------------------------------------------------
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int N, M, T, C, X, Y;

        //--------------------------------------------------------
        // Summary: Reads N, M, T, C values from the user using Scanner.
        // Precondition: Scanner must be open and inputs should be integer values.
        // Postcondition: N, M, T, C values are read and stored.
        //--------------------------------------------------------
        N = keyboard.nextInt();
        M = keyboard.nextInt();
        T = keyboard.nextInt();
        C = keyboard.nextInt();

        //--------------------------------------------------------
        // Summary: Initializes a Graph object with N+1 vertices.
        // Precondition: N is an integer representing the number of vertices.
        // Postcondition: A Graph object named road is created with N+1 vertices.
        //--------------------------------------------------------
        Graph road = new Graph(N+1);

        //--------------------------------------------------------
        // Summary: Reads M pairs of integers and adds each as an edge to the Graph.
        // Precondition: M pairs of integers to be read; M >= 0.
        // Postcondition: All M edges are added to the Graph road.
        //--------------------------------------------------------
        for (int i = 0; i < M; i++) {
            int v = keyboard.nextInt(); // 1-based index
            int w = keyboard.nextInt();
            road.addEdge(v, w);
        }

        //--------------------------------------------------------
        // Summary: Reads the starting point X and destination Y.
        // Precondition: X and Y are integers and valid vertices in the graph.
        // Postcondition: Values X and Y are read and stored.
        //--------------------------------------------------------
        X = keyboard.nextInt();
        Y = keyboard.nextInt();

        //--------------------------------------------------------
        // Summary: Closes the Scanner.
        // Precondition: Scanner was previously open.
        // Postcondition: Scanner is closed to prevent resource leak.
        //--------------------------------------------------------
        keyboard.close();

        //--------------------------------------------------------
        // Summary: Initializes a BreadthFirstPaths object and prints the shortest path from X to Y.
        // Precondition: BreadthFirstPaths class is defined and takes parameters road, X, T, and C.
        // Postcondition: Shortest path from X to Y is printed.
        //--------------------------------------------------------
        BreadthFirstPaths deneme = new BreadthFirstPaths(road, X, T, C);
        deneme.printRoad(X, Y);
    }
}
