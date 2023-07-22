package dev.rhyme.rocket;

import dev.rhyme.Item;

public interface SpaceShip {
    boolean launch();

    boolean land();

    boolean canCarry(Item item);

    void carry(Item item);
}
