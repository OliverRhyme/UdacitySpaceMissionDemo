package dev.rhyme.rocket;

import dev.rhyme.Item;

public abstract class Rocket implements SpaceShip {

    public final int cost;
    public final int weight;
    public final int maxWeight;

    protected int cargoWeight;

    protected Rocket(int cost, int weight, int maxWeight) {
        this.cost = cost;
        this.weight = weight;
        this.maxWeight = maxWeight;
    }

    @Override
    public boolean launch() {
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }

    @Override
    public boolean canCarry(Item item) {
        return cargoWeight + weight + item.weight() <= maxWeight;
    }

    @Override
    public void carry(Item item) {
        cargoWeight += item.weight();
    }
}
