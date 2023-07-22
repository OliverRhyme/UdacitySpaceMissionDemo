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
        ArrayList<Rocket> u1Phase1Rockets = simulation.loadU1(phase1Items);
        ArrayList<Rocket> u1Phase2Rockets = simulation.loadU1(phase2Items);
        int u1Total = getTotalAndPrintSimulation(simulation, "U1 Simulation", u1Phase1Rockets, u1Phase2Rockets);

        // U2 Simulation

        ArrayList<Rocket> u2Phase1Rockets = simulation.loadU2(phase1Items);
        ArrayList<Rocket> u2Phase2Rockets = simulation.loadU2(phase2Items);
        int u2Total = getTotalAndPrintSimulation(simulation, "U2 Simulation", u2Phase1Rockets, u2Phase2Rockets);

        // Print conclusion
        System.out.println("Conclusion");
        System.out.println("==========");
        if (u1Total < u2Total) {
            System.out.println("U1 is cheaper than U2 by " + formatCurrency(u2Total - u1Total));
        } else {
            System.out.println("U2 is cheaper than U1 by " + formatCurrency(u1Total - u2Total));
        }
    }

    private static int getTotalAndPrintSimulation(
            Simulation simulation,
            String title,
            ArrayList<Rocket> phase1,
            ArrayList<Rocket> phase2
    ) {
        System.out.println(title);
        System.out.println("=============");

        Result phase1Result = simulation.runSimulation(phase1);
        Result phase2Result = simulation.runSimulation(phase2);

        System.out.println("Phase 1 Rocket Count: " + phase1Result.rocketCount());
        System.out.println("Phase 1 Estimated Cost: " + formatCurrency(phase1Result.totalCost()));
        System.out.println("Phase 2 Rocket Count: " + phase2Result.rocketCount());
        System.out.println("Phase 2 Estimated Cost: " + formatCurrency(phase2Result.totalCost()));

        int total = phase1Result.totalCost() + phase2Result.totalCost();
        System.out.println("Total Estimated Cost: " + formatCurrency(total));

        System.out.println();

        return total;
    }
}