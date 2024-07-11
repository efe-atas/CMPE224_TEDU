//-----------------------------------------------------
// Title: HW3_Q1_solution
// Author: İsmail Efe Ataş
// ID: 
// Section: 1
// Assignment: 3
// Description: Main method to execute the program.
//-----------------------------------------------------
import java.util.ArrayList;

public class HW3_Q1_solution {

    //--------------------------------------------------------
    // Summary: Main method to execute the program. It initializes the 
    // Valuefinder object, calculates the Minimum Spanning Tree (MST) using 
    // Kruskal's algorithm, and prints the MST edges and their total weight.
    // Precondition: "HW3_Q1.txt" file exists and has valid format.
    // Postcondition: Prints the MST edges and the total weight of the MST.
    //--------------------------------------------------------
    public static void main(String[] args) {
        Valuefinder vf = new Valuefinder();
        ArrayList<Edge> mst = vf.graph.kruskalMST();
        int total_weight = 0;

        System.out.println("The Minimum Spanning Tree Path");
        for (Edge e : mst) {
            System.out.println(e.v + " " + e.w + " " + e.weight);
            total_weight += e.weight;
        }
        System.out.println();
        System.out.println("The Minimum Spanning Tree value= " + total_weight);
    }
}
