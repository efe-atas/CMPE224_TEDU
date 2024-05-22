//-----------------------------------------------------
// Title: HW2_Q2_solution Class
// Author: İsmail Efe Ataş
// ID: 10750010914
// Section: 1
// Assignment: Homework2_Question_2
// Description: This class is the main entry point for the program.
//-----------------------------------------------------
import java.util.*;

// Class representing a directed graph with additional functionality to identify non-connected neurons
class DirectGraph {

    Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
    ArrayList<List<Integer>> nonConnectedNeuron = new ArrayList<>();

    //--------------------------------------------------------
    // Summary: Adds an edge between two vertices in the graph.
    // Precondition: x and y are integers representing vertices.
    // Postcondition: An edge from x to y is added to the graph.
    //--------------------------------------------------------
    public void addEdge(int x, int y) {
        graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
    }

    //--------------------------------------------------------
    // Summary: Prints the entire graph with vertices and their neighbors.
    // Precondition: None
    // Postcondition: Graph is printed to the console.
    //--------------------------------------------------------
    public void printGraph() {
        for (Map.Entry<Integer, ArrayList<Integer>> entry : graph.entrySet()) {
            System.out.print(entry.getKey() + " ");
            for (int neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    //--------------------------------------------------------
    // Summary: Performs depth-first search (DFS) to find paths with no outgoing edges from the last node.
    // Precondition: start is the starting vertex, path is the current path, visited is a set of visited vertices, graph is the current graph instance.
    // Postcondition: All paths with no outgoing edges are stored in nonConnectedNeuron.
    //--------------------------------------------------------
    public void dfs(int start, List<Integer> path, Set<Integer> visited, DirectGraph graph) {
        if (path.size() > 1 && !graph.hasOutgoingEdges(path.get(path.size() - 1))) {
            nonConnectedNeuron.add(new ArrayList<>(path));
            return;
        }

        List<Integer> neighbors = graph.getNeighbors(start);
        for (Integer neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                dfs(neighbor, newPath, visited, graph);
                visited.remove(neighbor);
            }
        }
    }

    //--------------------------------------------------------
    // Summary: Checks if a node has outgoing edges.
    // Precondition: node is an integer representing a vertex.
    // Postcondition: Returns true if node has outgoing edges, false otherwise.
    //--------------------------------------------------------
    public boolean hasOutgoingEdges(int node) {
        return graph.containsKey(node) && !graph.get(node).isEmpty();
    }

    //--------------------------------------------------------
    // Summary: Prints paths representing neurons that do not connect to other neurons.
    // Precondition: None
    // Postcondition: Prints all paths that do not lead to other paths.
    //--------------------------------------------------------
    public void printNonConnectedNeurons() {
        int max_size = Integer.MIN_VALUE;
        for(List<Integer> x : nonConnectedNeuron) {
            if(x.size() > max_size)
                max_size = x.size();
        }

        nonConnectedNeuron.sort(Comparator.comparingInt(List::size));
        System.out.println("Result is");
        for(List<Integer> x : nonConnectedNeuron) {
            if(x.size() < max_size) {
                for(int vertex : x) {
                    System.out.print(vertex + " ");
                }
                System.out.println();
            }
        }
    }

    //--------------------------------------------------------
    // Summary: Gets the neighbors of a vertex.
    // Precondition: start is an integer representing a vertex.
    // Postcondition: Returns a list of integers representing the neighbors of the vertex.
    //--------------------------------------------------------
    private List<Integer> getNeighbors(int start) {
        return graph.get(start);
    }
}

// Main class for HW2 Question 2 solution.
public class HW2_Q2_solution {
    //--------------------------------------------------------
    // Summary: Entry point for HW2 Question 2.
    // Precondition: None
    // Postcondition: Executes operations to process a graph and identify non-connected neurons.
    //--------------------------------------------------------
    public static void main(String[] args) {
        String filePath = "HW2_Q2_text.txt";
        DirectGraph graph = new DirectGraph();

        List<String> lines = FileRead.readLines(filePath);
        ValueFinder.processLines(graph, lines);

        System.out.println("V=" + ValueFinder.V);
        System.out.println("E=" + ValueFinder.E);
        for (String input : lines) {
            if (input.length() > 2) {
                String[] parts = input.split(" ");
                int part1 = Integer.parseInt(parts[0]);
                int part2 = Integer.parseInt(parts[1]);
                System.out.println(part1 + " " + part2);
            }
        }

        System.out.println();
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        path.add(0); // Start path
        graph.dfs(0, path, visited, graph);
        graph.printNonConnectedNeurons();
    }
}
