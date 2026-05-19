public class Task1 {
    public static Graph createGraph() {
        Graph graph = new Graph();

        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');

        graph.addEdge('B', 'A', 1);
        graph.addEdge('C', 'A', 6);
        graph.addEdge('D', 'C', 8);
        graph.addEdge('E', 'D', 11);
        graph.addEdge('C', 'E', 1);
        graph.addEdge('B', 'C', 9);

        return graph;
    }

    public static void runTask1() {
        Graph graph = createGraph();
        graph.printGraph();
    }
}
