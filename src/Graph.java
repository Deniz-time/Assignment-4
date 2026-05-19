import java.util.*;

public class Graph {
    public static class Edge {
        char target;
        int weight;

        public Edge(char target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    private final Map<Character, List<Edge>> adjList;

    public Graph() {
        this.adjList = new TreeMap<>();
    }

    public void addVertex(char v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(char u, char v, int weight) {
        addVertex(u);
        addVertex(v);
        adjList.get(u).add(new Edge(v, weight));
        adjList.get(v).add(new Edge(u, weight)); // Так как граф неориентированный
    }

    public Map<Character, List<Edge>> getAdjList() {
        return adjList;
    }

    public void printGraph() {
        for (Map.Entry<Character, List<Edge>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            List<String> edgesStr = new ArrayList<>();
            List<Edge> edges = new ArrayList<>(entry.getValue());
            edges.sort(Comparator.comparingInt(e -> e.target));

            for (Edge edge : edges) {
                edgesStr.add(edge.target + "(" + edge.weight + ")");
            }
            System.out.println(String.join(", ", edgesStr));
        }
    }
}