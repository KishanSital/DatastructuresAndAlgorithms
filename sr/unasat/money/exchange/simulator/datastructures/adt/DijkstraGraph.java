package sr.unasat.money.exchange.simulator.datastructures.adt;

import sr.unasat.money.exchange.simulator.entities.Vertex;
import sr.unasat.money.exchange.simulator.entities.Weight;

public interface DijkstraGraph {

    void addVertex(Vertex lab);

    void addEdge(int start, int end, Weight weightFactor);

    void path(int startTree);

    void displayPaths();

    double[][] getEdges();

    void setEdges(double[][] adjMat);

    void addVertexList(List<Vertex> vertexList);

}
