package Graph;

class AdjacentVertex {
    String vertexName;
    AdjacentVertex nextAdjVertex;

    public AdjacentVertex(String vertexName) {
        this.vertexName = vertexName;
        this.nextAdjVertex = null;
    }
}

class Vertex {
    String vertexName;
    Vertex nextVertex;              // pointer to next vertex in graph
    AdjacentVertex firstAdjVertex;  // pointer to head of adjacency list

    public Vertex(String vertexName) {
        this.vertexName = vertexName;
        this.nextVertex = null;
        this.firstAdjVertex = null;
    }
}

public class AdjacencyList {
    Vertex head; // start of vertex list

    // Add a vertex
    public void addVertex(String name) {
        Vertex newVertex = new Vertex(name);
        if (head == null) {
            head = newVertex;
        } else {
            Vertex temp = head;
            while (temp.nextVertex != null) {
                temp = temp.nextVertex;
            }
            temp.nextVertex = newVertex;
        }
    }

    // Find a vertex by name
    public Vertex findVertex(String name) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.vertexName.equals(name)) {
                return temp;
            }
            temp = temp.nextVertex;
        }
        return null;
    }

    // Add edge (directed)
    public void addEdge(String from, String to) {
        Vertex fromVertex = findVertex(from);
        if (fromVertex == null) {
            System.out.println("Vertex " + from + " not found.");
            return;
        }

        AdjacentVertex newAdj = new AdjacentVertex(to);

        if (fromVertex.firstAdjVertex == null) {
            fromVertex.firstAdjVertex = newAdj;
        } else {
            AdjacentVertex temp = fromVertex.firstAdjVertex;
            while (temp.nextAdjVertex != null) {
                temp = temp.nextAdjVertex;
            }
            temp.nextAdjVertex = newAdj;
        }
    }

    // Display adjacency list
    public void display() {
        Vertex temp = head;
        while (temp != null) {
            System.out.print(temp.vertexName + " → ");
            AdjacentVertex adj = temp.firstAdjVertex;
            while (adj != null) {
                System.out.print(adj.vertexName + " ");
                adj = adj.nextAdjVertex;
            }
            System.out.println();
            temp = temp.nextVertex;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        AdjacencyList graph = new AdjacencyList();

        String[] vertices = {"v1", "v2", "v3", "v4", "v5", "v6"};
        for (String v : vertices) {
            graph.addVertex(v);
        }

        String[][] edges = {
            {"v1", "v2"},
            {"v1", "v4"},
            {"v2", "v3"},
            {"v3", "v4"},
            {"v3", "v6"},
            {"v3", "v5"},
            {"v5", "v6"},
            {"v6", "v4"}
        };

        for (String[] e : edges) {
            graph.addEdge(e[0], e[1]);
        }
        graph.display();
    }
}
