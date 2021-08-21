package sr.unasat.money.exchange.simulator.entities;

public class Weight {
    private double milesAway;
    private double approximateTravelHours;
    private Cambio cambio;

    public Weight(double milesAway, double approximateTravelHours, Cambio cambio) {
        this.milesAway = milesAway;
        this.approximateTravelHours = approximateTravelHours;
        this.cambio = cambio;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public double getMilesAway() {
        return milesAway;
    }

    public void setMilesAway(double milesAway) {
        this.milesAway = milesAway;
    }

    public double getApproximateTravelHours() {
        return approximateTravelHours;
    }

    public void setApproximateTravelHours(double approximateTravelHours) {
        this.approximateTravelHours = approximateTravelHours;
    }

    public double calculateWeightOfExpensesInUSD(double mileagePerLiter,
                                                 double priceOfOneLiterInSRD,
                                                 double hourlyNetIncomeInSRD) {
        double gasExpensesInSRD = (this.getMilesAway() / (mileagePerLiter * priceOfOneLiterInSRD));
        double timeExpensesInSRD = (this.getApproximateTravelHours() * hourlyNetIncomeInSRD);
        double gasAndTimeExpensesInUSD = (gasExpensesInSRD + timeExpensesInSRD) / this.cambio.getSRDToUSDCurrencyRate();
        return gasAndTimeExpensesInUSD;
    }

    public double calculateWeightOfLeftOverMoneyAfterExchangeAndExpensesInUSD(double mileagePerLiter,
                                                                              double priceOfOneLiterInSRD,
                                                                              double hourlyNetIncomeInSRD,
                                                                              double moneyToExchangeFromSRDToUSD) {
        double gasExpensesInSRD = (this.getMilesAway() / (mileagePerLiter * priceOfOneLiterInSRD));
        double timeExpensesInSRD = (this.getApproximateTravelHours() * hourlyNetIncomeInSRD);
        double USDAfterCurrencyExchange = moneyToExchangeFromSRDToUSD / this.cambio.getSRDToUSDCurrencyRate();
        double gasAndTimeExpensesInUSD = (gasExpensesInSRD + timeExpensesInSRD) / this.cambio.getSRDToUSDCurrencyRate();
        return USDAfterCurrencyExchange - gasAndTimeExpensesInUSD;
    }

}
