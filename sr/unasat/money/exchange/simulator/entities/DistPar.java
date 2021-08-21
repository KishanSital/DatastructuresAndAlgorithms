package sr.unasat.money.exchange.simulator.entities;

public class DistPar {

    private double distance;   // distance from start to this vertex
    private int parentVert; // current parent of this vertex

    // -------------------------------------------------------------
    public DistPar(int pv, double d)  // constructor
    {
        distance = d;
        parentVert = pv;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getParentVert() {
        return parentVert;
    }

    public void setParentVert(int parentVert) {
        this.parentVert = parentVert;
    }
}
