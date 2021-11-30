package graph;

import java.util.Stack;

public class DepthFirstTraversal {
    private final boolean[] marked;    // marked[v] = is there an s-v path?
    private final int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    public DepthFirstTraversal(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.v];
        marked = new boolean[G.v];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adjLists[v]) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
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
        DepthFirstTraversal dfs = new DepthFirstTraversal(g, s);
        for (int v = 0; v < g.v; v++) {
            if (dfs.hasPathTo(v)) {
                Iterable<Integer> integers = dfs.pathTo(v);
                System.out.println(integers);
            } else {
                System.out.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

}
