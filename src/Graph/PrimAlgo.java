package Graph;

import java.util.Arrays;
//Start with any vertex
//elect edge with min cost adjacent to vertex (no cycles)
public class PrimAlgo {

    static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    static void primMST(int[][] graph) {
        int V = graph.length;
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) { // v-1 edges
            int u = minKey(key, visited); // vertex not in mst with smallest edge
            visited[u] = true;

            System.out.println("\nStep " + (count + 1) + ": Add vertex " + u + " to MST");
            System.out.println("Current MST vertices: " + Arrays.toString(visited));

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        // Print MST
        int totalWeight = 0;
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("Total weight: " + totalWeight);
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, 0, 0, 0, 0, 0, 3},
            {2, 0, 5, 10, 0, 0, 0, 7},
            {0, 5, 0, 9, 0, 0, 0, 0},
            {0, 10, 9, 0, 11, 0, 0, 9},
            {0, 0, 0, 11, 0, 8, 0, 0},
            {0, 0, 0, 0, 8, 0, 6, 0},
            {0, 0, 0, 0, 0, 6, 0, 4},
            {3, 7, 0, 9, 0, 0, 4, 0}
        };

        primMST(graph);
    }
}
