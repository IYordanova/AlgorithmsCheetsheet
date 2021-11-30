package graph;

import java.util.LinkedList;

public class Graph {
    final LinkedList<Integer> adjLists[];
    final int v;

    Graph(int vertices) {
        v = vertices;
        adjLists = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjLists[i] = new LinkedList<>();
        }
    }

    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

}
