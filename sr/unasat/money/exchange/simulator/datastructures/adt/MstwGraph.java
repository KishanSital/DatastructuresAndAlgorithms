package sr.unasat.money.exchange.simulator.datastructures.adt;

import sr.unasat.money.exchange.simulator.entities.Vertex;
import sr.unasat.money.exchange.simulator.entities.Weight;

public interface MstwGraph {
    void addVertex(Vertex lab);

    void addEdge(int start, int end, Weight weightFactor);

    void displayVertex(int v);

    void mstw(int startingIndex);

    void putInPQ(int newVert, double newDist);

    void getTotal ();

    double[][] getEdges();

    void setEdges(double[][] adjMat);

    void addVertexList(List<Vertex> vertexList);

}
