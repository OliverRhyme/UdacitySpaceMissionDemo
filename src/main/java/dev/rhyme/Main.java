package dev.rhyme;

import dev.rhyme.rocket.Rocket;
import dev.rhyme.simulation.Result;
import dev.rhyme.simulation.Simulation;

import java.util.ArrayList;

public class Main {

    private static String formatCurrency(int amount) {
        return String.format("$%.2fM", amount / 1000000.0);
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        ArrayList<Item> phase1Items = simulation.loadItems("/phase-1.txt");
        ArrayList<Item> phase2Items = simulation.loadItems("/phase-2.txt");

        // U1 Simulation

        System.out.println("U1 Simulation");
        System.out.println("=============");

        ArrayList<Rocket> u1Phase1Rockets = simulation.loadU1(phase1Items);
        Result u1Phase1Result = simulation.runSimulation(u1Phase1Rockets);

        ArrayList<Rocket> u1Phase2Rockets = simulation.loadU1(phase2Items);
        Result u1Phase2Result = simulation.runSimulation(u1Phase2Rockets);

        System.out.println("Phase 1 Estimated Rockets: " + u1Phase1Result.rocketCount());
        System.out.println("Phase 1 Estimated Cost: " + formatCurrency(u1Phase1Result.totalCost()));
        System.out.println("Phase 2 Rocket Count: " + u1Phase2Result.rocketCount());
        System.out.println("Phase 2 Estimated Cost: " + formatCurrency(u1Phase2Result.totalCost()));

        int u1Total = u1Phase1Result.totalCost() + u1Phase2Result.totalCost();
        System.out.println("Total Estimated Cost: " + formatCurrency(u1Total));

        System.out.println();

        // U2 Simulation

        System.out.println("U2 Simulation");
        System.out.println("=============");

        ArrayList<Rocket> u2Phase1Rockets = simulation.loadU2(phase1Items);
        Result u2Phase1Result = simulation.runSimulation(u2Phase1Rockets);

        ArrayList<Rocket> u2Phase2Rockets = simulation.loadU2(phase2Items);
        Result u2Phase2Result = simulation.runSimulation(u2Phase2Rockets);

        System.out.println("Phase 1 Rocket Count: " + u2Phase1Result.rocketCount());
        System.out.println("Phase 1 Estimated Cost: " + formatCurrency(u2Phase1Result.totalCost()));
        System.out.println("Phase 2 Rocket Count: " + u2Phase2Result.rocketCount());
        System.out.println("Phase 2 Estimated Cost: " + formatCurrency(u2Phase2Result.totalCost()));

        int u2Total = u2Phase1Result.totalCost() + u2Phase2Result.totalCost();
        System.out.println("Total Estimated Cost: " + formatCurrency(u2Total));

        System.out.println();

        // print conclusion
        System.out.println("Conclusion");
        System.out.println("==========");
        if (u1Total < u2Total) {
            System.out.println("U1 is cheaper than U2 by " + formatCurrency(u2Total - u1Total));
        } else {
            System.out.println("U2 is cheaper than U1 by " + formatCurrency(u1Total - u2Total));
        }
    }
}