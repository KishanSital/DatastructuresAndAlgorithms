package sr.unasat.money.exchange.simulator.entities;

public class Cambio {

    private double SRDToUSDCurrencyRate;
    private String cambioName;

    public Cambio(double SRDToUSDCurrencyRate, String cambioName) {
        this.SRDToUSDCurrencyRate = SRDToUSDCurrencyRate;
        this.cambioName = cambioName;
    }

    public double getSRDToUSDCurrencyRate() {
        return SRDToUSDCurrencyRate;
    }

    public void setSRDToUSDCurrencyRate(double SRDToUSDCurrencyRate) {
        this.SRDToUSDCurrencyRate = SRDToUSDCurrencyRate;
    }

    @Override
    public String toString() {
        return "Cambio{" +
                "SRDToUSDCurrencyRate=" + SRDToUSDCurrencyRate +
                ", cambioName='" + cambioName + '\'' +
                '}';
    }
}
