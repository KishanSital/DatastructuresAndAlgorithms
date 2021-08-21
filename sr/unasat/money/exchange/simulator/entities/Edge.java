package sr.unasat.money.exchange.simulator.entities;

public class Edge {
    public int srcVert;   // index of a vertex starting edge
    public int destVert;  // index of a vertex ending edge
    public Double distance;  // distance from src to dest

    // -------------------------------------------------------------
    public Edge(int sv, int dv, Double d)  // constructor
    {
        srcVert = sv;
        destVert = dv;
        distance = d;
    }
// -------------------------------------------------------------
}
