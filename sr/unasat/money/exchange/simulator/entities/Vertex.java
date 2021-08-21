package sr.unasat.money.exchange.simulator.entities;

public class Vertex {

    private Locatie locatie;
    private boolean isInTree;

    public Vertex(Locatie lab) {
        locatie = lab;
        isInTree = false;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public boolean isInTree() {
        return isInTree;
    }

    public void setInTree(boolean inTree) {
        isInTree = inTree;
    }
}
