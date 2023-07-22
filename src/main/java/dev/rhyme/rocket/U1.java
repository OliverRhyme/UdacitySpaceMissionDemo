package dev.rhyme.rocket;

public final class U1 extends Rocket {

    private static final int COST = 100_000_000; // $100 Million
    private static final int WEIGHT = 10_000; // 10 Tonnes
    private static final int MAX_WEIGHT = 18_000; // 18 Tonnes

    public U1() {
        super(COST, WEIGHT, MAX_WEIGHT);
    }

    @Override
    public boolean launch() {
        // 5% * (cargo carried / cargo limit)
        return Math.random() > 0.05 * (cargoWeight / (double) maxWeight);

    }

    @Override
    public boolean land() {
        // 1% * (cargo carried / cargo limit)
        return Math.random() > 0.01 * (cargoWeight / (double) maxWeight);
    }
}
