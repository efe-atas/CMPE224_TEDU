//----------------------------------------------------
// Title: DirectGraph Class
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: Homework2_Question_1
// Description:  Class representing a directed graph using adjacency list
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DirectGraph {
    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    //--------------------------------------------------------
    // Summary: Adds an edge from source to destination in the graph.
    // Precondition: source and destination are integers
    // Postcondition: An edge is added to the graph's adjacency list.
    //--------------------------------------------------------
    public void addEdge(int source, int destination) {
        List<Integer> destinations = this.adjacencyList.get(source);

        if (destinations == null) {
            destinations = new ArrayList<>();
            this.adjacencyList.put(source, destinations);
        }

        destinations.add(destination);
    }

    //--------------------------------------------------------
    // Summary: Displays two-step destinations from a given start point.
    // Precondition: start is an integer representing the starting vertex
    // Postcondition: Two-step destinations are printed to the console.
    //--------------------------------------------------------
    public void getTwoStepDestinations(int start) {
        List<Integer> destinations = adjacencyList.get(start);

        for (int destination : destinations) {
            List<Integer> second_destinations = adjacencyList.get(destination);

            for(int second_destination : second_destinations){
                System.out.println(start + " " + destination + " " + second_destination);
            }
        }
    }
}