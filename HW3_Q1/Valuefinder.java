//-----------------------------------------------------
// Title: Valuefinder
// Author: İsmail Efe Ataş
// ID: 
// Section: 1
// Assignment: 3
// Description: Constructor that initializes a Valuefinder object, reads the graph data from a file, creates the graph, and adds edges.
//-----------------------------------------------------
import java.util.ArrayList;
import java.util.*;

public class Valuefinder {
    Graph graph;

    //--------------------------------------------------------
    // Summary: Constructor that initializes a Valuefinder object, reads 
    // the graph data from a file, creates the graph, and adds edges.
    // Precondition: "HW3_Q1.txt" file exists and has valid format with 
    // first line as number of vertices, second line as number of edges, 
    // and subsequent lines representing edges with their weights.
    // Postcondition: Graph is created and printed.
    //--------------------------------------------------------
    Valuefinder() {
        ArrayList<String> lines = FileRead.readLines("HW3_Q1.txt");
        int V = Integer.parseInt(lines.get(0));
        int E = Integer.parseInt(lines.get(1));
        System.out.println("V=" + V);
        System.out.println("E=" + E);

        graph = new Graph(V);

        ArrayList<Edge> allEdges = new ArrayList<>();

        for (int i = 2; i < E + 2; i++) {
            String[] separated = lines.get(i).split(" ");
            int v = Integer.parseInt(separated[0]);
            int w = Integer.parseInt(separated[1]);
            int weight = Integer.parseInt(separated[2]);
            allEdges.add(new Edge(v, w, weight));
            allEdges.add(new Edge(w, v, weight));
        }

        for (Edge edge : allEdges) {
            graph.addEdge(edge.v, edge.w, edge.weight);
        }

        graph.printGraph();
        System.out.println();
    }
}

class Graph {
    ArrayList<Edge> edges = new ArrayList<>();
    int V;

    //--------------------------------------------------------
    // Summary: Constructor that initializes a Graph object with a 
    // specified number of vertices.
    // Precondition: V is an integer representing the number of vertices.
    // Postcondition: Graph object is created.
    //--------------------------------------------------------
    Graph(int V) {
        this.V = V;
    }

    //--------------------------------------------------------
    // Summary: Adds an edge to the graph.
    // Precondition: v, w, and weight are integers representing the 
    // vertices and weight of the edge.
    // Postcondition: Edge is added to the list of edges in the graph.
    //--------------------------------------------------------
    public void addEdge(int v, int w, int weight) {
        edges.add(new Edge(v, w, weight));
    }

    //--------------------------------------------------------
    // Summary: Prints the graph's edges in a sorted order.
    // Precondition: None.
    // Postcondition: Edges are printed in sorted order based on vertices.
    //--------------------------------------------------------
    public void printGraph() {
        edges.sort(Comparator.comparingInt((Edge e) -> e.v).thenComparingInt(e -> e.w));
        for (Edge edge : edges) {
            System.out.println(edge.v + " " + edge.w + " " + edge.weight);
        }
        System.out.println();
    }

    //--------------------------------------------------------
    // Summary: Finds the Minimum Spanning Tree (MST) using Kruskal's 
    // algorithm.
    // Precondition: None.
    // Postcondition: Returns an ArrayList of edges that are part of the MST.
    //--------------------------------------------------------
    public ArrayList<Edge> kruskalMST() {
        Collections.sort(edges);

        UnionFind uf = new UnionFind(V);
        ArrayList<Edge> result = new ArrayList<>();

        for (Edge edge : edges) {
            int root1 = uf.find(edge.v);
            int root2 = uf.find(edge.w);

            if (root1 != root2) {
                result.add(edge);
                uf.union(root1, root2);  // Union the two sets
            }

            if (result.size() == V - 1) break;
        }

        return result;
    }
}

class Edge implements Comparable<Edge> {
    final int v, w;
    final int weight;

    //--------------------------------------------------------
    // Summary: Constructor that initializes an Edge object.
    // Precondition: v, w, and weight are integers representing the 
    // vertices and weight of the edge.
    // Postcondition: Edge object is created.
    //--------------------------------------------------------
    Edge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    //--------------------------------------------------------
    // Summary: Compares this edge with another edge based on weight.
    // Precondition: that is an Edge object to be compared.
    // Postcondition: Returns a negative integer, zero, or a positive 
    // integer as this edge's weight is less than, equal to, or greater 
    // than the specified edge's weight.
    //--------------------------------------------------------
    @Override
    public int compareTo(Edge that) {
        return Integer.compare(this.weight, that.weight);
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    //--------------------------------------------------------
    // Summary: Constructor that initializes a UnionFind object.
    // Precondition: size is an integer representing the number of elements.
    // Postcondition: UnionFind object is created with each element as its 
    // own parent and rank initialized to 0.
    //--------------------------------------------------------
    UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    //--------------------------------------------------------
    // Summary: Finds the root of the element p with path compression.
    // Precondition: p is an integer representing the element.
    // Postcondition: Returns the root of the element p.
    //--------------------------------------------------------
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    //--------------------------------------------------------
    // Summary: Unites the sets containing elements p and q.
    // Precondition: p and q are integers representing the elements.
    // Postcondition: The sets containing p and q are united.
    //--------------------------------------------------------
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
    }
}
