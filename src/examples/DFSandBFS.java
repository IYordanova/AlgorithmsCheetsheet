package examples;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DFSandBFS {

    static class Vertex {
        int value;
        List<Integer> adjIndexes;
        boolean wasVisited;
    }

    private static int getAdjUnvisitedVertex(Vertex[] vertexList, int v) {
        return vertexList[v].adjIndexes.stream()
                .filter(adjV -> !vertexList[adjV].wasVisited)
                .findFirst()
                .orElse(-1);
    }


    public void dfs(Vertex[] vertexList) {
        // Begin at vertex 0 (A)
        vertexList[0].wasVisited = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int adjacentVertexIndex = getAdjUnvisitedVertex(vertexList, stack.peek());
            // If no such vertex
            if (adjacentVertexIndex == -1) {
                stack.pop();
            } else {
                vertexList[adjacentVertexIndex].wasVisited = true;
                // TODO: Do something
                stack.push(adjacentVertexIndex);
            }
        }
        // Stack is empty, so we're done, reset flags
        for (Vertex vertex : vertexList) {
            vertex.wasVisited = false;
        }
    }

    public void bfs(Vertex[] vertexList) {
        vertexList[0].wasVisited = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int v2;
        while (!queue.isEmpty()) {
            int v1 = queue.remove();
            // Until it has no unvisited neighbors, get one
            while ((v2 = getAdjUnvisitedVertex(vertexList, v1)) != -1) {
                vertexList[v2].wasVisited = true;
                // TODO: Do something
                queue.add(v2);
            }
        }
        // Queue is empty, so we're done, reset flags
        for (Vertex vertex : vertexList) {
            vertex.wasVisited = false;
        }
    }
}
