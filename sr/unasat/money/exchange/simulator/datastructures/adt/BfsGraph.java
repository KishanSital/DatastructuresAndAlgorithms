package sr.unasat.money.exchange.simulator.datastructures.adt;

import sr.unasat.money.exchange.simulator.entities.Vertex;

public interface BfsGraph {

    void addVertexList(List<Vertex> vertexList);

    void addVertex(Vertex vertex);

    void addEdge(int startPosition, int endPosition);

    double[][] getEdges();

    void setEdges(double[][] adjMat);

    void displayParentVertex(int vertexIndex);

    void displayAdjacentVertex(int vertexIndex);

    void bfs(int startingPosition);

    int getAdjUnvisitedVertex(int vertexIndex);
}
