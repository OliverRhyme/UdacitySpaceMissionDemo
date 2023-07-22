package dev.rhyme.simulation;

import dev.rhyme.Item;
import dev.rhyme.rocket.Rocket;
import dev.rhyme.rocket.U1;
import dev.rhyme.rocket.U2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

public class Simulation {
    public ArrayList<Item> loadItems(String fileName) {
        Scanner scanner = new Scanner(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
        ArrayList<Item> items = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("=");
            items.add(new Item(line[0], Integer.parseInt(line[1])));
        }
        return items;
    }

    private ArrayList<Rocket> loadRocket(ArrayList<Item> items, Supplier<Rocket> createRocket) {
        items.sort((o1, o2) -> Integer.compare(o2.weight(), o1.weight()));

        ArrayList<Rocket> rockets = new ArrayList<>();

        Rocket rocket = createRocket.get();

        for (Item item : items) {
            if (!tryCarry(rocket, item)) {
                rockets.add(rocket); // Ship is full, add it to the list
                rocket = createRocket.get();
                tryCarry(rocket, item); // Try to carry the item again, this should work unless the item is too heavy
            }
        }

        rockets.add(rocket);
        return rockets;
    }

    private boolean tryCarry(Rocket rocket, Item item) {
        if (rocket.canCarry(item)) {
            rocket.carry(item);
            return true;
        }
        return false;
    }

    public ArrayList<Rocket> loadU1(ArrayList<Item> items) {
        return loadRocket(items, U1::new);

//        items.sort((o1, o2) -> Integer.compare(o2.weight(), o1.weight()));
//
//        ArrayList<Rocket> rockets = new ArrayList<>();
//
//        U1 rocket = new U1();
//
//        for (Item item : items) {
//            if (!tryCarry(rocket, item)) {
//                rockets.add(rocket); // Ship is full, add it to the list
//                rocket = new U1();
//                tryCarry(rocket, item); // Try to carry the item again, this should work unless the item is too heavy
//            }
//        }
//
//        rockets.add(rocket);
//        return rockets;
    }

    public ArrayList<Rocket> loadU2(ArrayList<Item> items) {
        return loadRocket(items, U2::new);

//        items.sort((o1, o2) -> Integer.compare(o2.weight(), o1.weight()));
//
//        ArrayList<Rocket> rockets = new ArrayList<>();
//
//        U2 rocket = new U2();
//
//        for (Item item : items) {
//            if (!tryCarry(rocket, item)) {
//                rockets.add(rocket); // Ship is full, add it to the list
//                rocket = new U2();
//                tryCarry(rocket, item); // Try to carry the item again, this should work unless the item is too heavy
//            }
//        }
//
//        rockets.add(rocket);
//        return rockets;
    }

    public Result runSimulation(ArrayList<Rocket> rockets) {
        int rocketCount = 0;
        int totalCost = 0;
        for (Rocket rocket : rockets) {
            rocketCount++;
            totalCost += rocket.cost;

            // Keep launching and landing until successful
            while (!rocket.launch() || !rocket.land()) {
                rocketCount++;
                totalCost += rocket.cost;
            }
        }
        return new Result(rocketCount, totalCost);
    }
}
