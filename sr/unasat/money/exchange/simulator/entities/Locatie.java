package sr.unasat.money.exchange.simulator.entities;

public class Locatie {

    private String LocatieName;

    public Locatie(String LocatieName) {
        this.LocatieName = LocatieName;
    }

    @Override
    public String toString() {
        return LocatieName;

    }

}
