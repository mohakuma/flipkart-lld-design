package vehiclerental.strategy;

public class RentingStrategyChain {
    private RentingStrategy c1;

    public RentingStrategy getC1() {
        return this.c1;
    }

    public RentingStrategyChain() {
        // initialize the chain;

        this.c1 = new DefaultPricingStrategy();
        RentingStrategy c2 = new LowestPriceStrategy();
        RentingStrategy c3 = new BestVehicleTypeStrategy();

        c1.setNextStrategy(c2);
        c2.setNextStrategy(c3);
        c3.setNextStrategy(null);
    }
}
