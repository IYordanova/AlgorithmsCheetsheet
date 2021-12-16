package examples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PrimKruskalMST {


    static class Prim {

        static int mst(int[][] G) {
            boolean[] selected = new boolean[G.length];
            int mst = 0;
            for (int v = 0; v < G.length; v++) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < G[v].length; i++) {
                    if (G[v][i] < min && !selected[i]) {
                        min = G[v][i];
                    }
                }

                if (min != Integer.MAX_VALUE) {
                    selected[v] = true;
                    mst += min;
                }
            }
            return mst;
        }
    }

    static class Kruskal {

        static class Edge implements Comparable<Edge> {
            int src, dest, weight;

            public int compareTo(Edge compareEdge) {
                return this.weight - compareEdge.weight;
            }
        }

        static class SubGraph {
            int parent, rank;
        }

        static class Graph {
            int V, E;
            Edge[] edge;

            Graph(int v, int e) {
                V = v;
                E = e;
                edge = new Edge[E];
                for (int i = 0; i < e; ++i)
                    edge[i] = new Edge();
            }

            int find(SubGraph[] subsets, int i) {
                if (subsets[i].parent != i)
                    subsets[i].parent = find(subsets, subsets[i].parent);
                return subsets[i].parent;
            }

            void union(SubGraph[] subsets, int x, int y) {
                int xroot = find(subsets, x);
                int yroot = find(subsets, y);

                if (subsets[xroot].rank < subsets[yroot].rank)
                    subsets[xroot].parent = yroot;
                else if (subsets[xroot].rank > subsets[yroot].rank)
                    subsets[yroot].parent = xroot;
                else {
                    subsets[yroot].parent = xroot;
                    subsets[xroot].rank++;
                }
            }
        }

        static int mst(Graph graph) {
            Edge[] result = new Edge[graph.V + 1];
            for (int i = 0; i < graph.V + 1; ++i) {
                result[i] = new Edge();
            }

            Arrays.sort(graph.edge);

            SubGraph[] subsets = new SubGraph[graph.V + 1];
            for (int i = 0; i < graph.V + 1; ++i) {
                subsets[i] = new SubGraph();
                subsets[i].parent = i;
                subsets[i].rank = 0;
            }

            int idx = 0;
            int e = 0;
            while (e < graph.V - 1) {
                Edge next_edge = graph.edge[idx++];

                int x = graph.find(subsets, next_edge.src);
                int y = graph.find(subsets, next_edge.dest);

                if (x != y) {
                    result[e++] = next_edge;
                    graph.union(subsets, x, y);
                }
            }

            int minimumCost = 0;
            for (int i = 0; i < e; ++i) {
                minimumCost += result[i].weight;
            }
            return minimumCost;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("f.txt")));
        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bufferedReader.close();

        int[][] pg = new int[gNodes + 1][gNodes + 1];
        for (int s = 0; s < gNodes + 1; s++) {
            for (int p = 0; p < gNodes + 1; p++) {
                pg[s][p] = Integer.MAX_VALUE;
            }
        }
        Kruskal.Graph kg = new Kruskal.Graph(gNodes, gEdges);

        for (int i = 0; i < gEdges; i++) {
            Integer from = gFrom.get(i);
            Integer to = gTo.get(i);
            Integer weight = gWeight.get(i);

            pg[from][to] = weight;
            pg[to][from] = weight;

            kg.edge[i].src = from;
            kg.edge[i].dest = to;
            kg.edge[i].weight = weight;
        }
        int res = Prim.mst(pg);
        System.out.println(res);

        res = Kruskal.mst(kg);
        System.out.println(res);
    }
}
