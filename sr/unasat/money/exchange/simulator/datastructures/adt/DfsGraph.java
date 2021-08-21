package sr.unasat.money.exchange.simulator.datastructures.adt;

import sr.unasat.money.exchange.simulator.entities.Vertex;

public interface DfsGraph {

    void addVertexList(List<Vertex> vertexList);

    // ------------------------------------------------------------
    void addVertex(Vertex lab);

    // ------------------------------------------------------------
    void addEdge(int start, int end);

    double[][] getEdges();

    void setEdges(double[][] adjMat);

    // ------------------------------------------------------------
    void displayParentVertex(int vertexIndex);

    boolean displayVertexWithDestination(int vertexIndex, int destinationIndex);

    // ------------------------------------------------------------
    void dfs(int startingIndex, int endingIndex)  // depth-first search  // end dfs
    ;

    // ------------------------------------------------------------
    // returns an unvisited vertex adj to v
    int getAdjUnvisitedVertex(int v)  // end getAdjUnvisitedVertex()
    ;
}
