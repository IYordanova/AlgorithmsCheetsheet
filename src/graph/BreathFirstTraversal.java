package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreathFirstTraversal {
    private static final int INFINITY = Integer.MAX_VALUE;
    private final boolean[] marked;  // marked[v] = is there an s-v path
    private final int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private final int[] distTo;      // distTo[v] = number of edges shortest s-v path

    public BreathFirstTraversal(Graph G, int s) {
        marked = new boolean[G.v];
        distTo = new int[G.v];
        edgeTo = new int[G.v];
        bfs(G, s);
    }


    // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < G.v; v++) {
            distTo[v] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        q.offer(s);

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adjLists[v]) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        int s = 0;
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 0);
        g.addEdge(0, 3);
        g.addEdge(3, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(4, 2);
        BreathFirstTraversal bfs = new BreathFirstTraversal(g, s);
        for (int v = 0; v < g.v; v++) {
            if (bfs.hasPathTo(v)) {
                Iterable<Integer> integers = bfs.pathTo(v);
                System.out.println(integers);
            } else {
                System.out.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

}
