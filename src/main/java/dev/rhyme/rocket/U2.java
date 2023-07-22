package dev.rhyme.rocket;

public final class U2 extends Rocket {

    private static final int COST = 120_000_000; // $120 Million
    private static final int WEIGHT = 18_000; // 18 Tonnes
    private static final int MAX_WEIGHT = 29_000; // 29 Tonnes

    public U2() {
        super(COST, WEIGHT, MAX_WEIGHT);
    }

    @Override
    public boolean launch() {
        // 4% * (cargo carried / cargo limit)
        return Math.random() > 0.04 * (cargoWeight / (double) maxWeight);

    }

    @Override
    public boolean land() {
        // 8% * (cargo carried / cargo limit)
        return Math.random() > 0.08 * (cargoWeight / (double) maxWeight);
    }
}
