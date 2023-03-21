package view;

import domain.BetAmount;

public class Gain {

    private final String name;
    private final double betAmount;

    public Gain(String name, BetAmount betAmount) {
        this.name = name;
        this.betAmount = betAmount.toDouble();
    }

    public Gain(String name, double betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public String getName() {
        return name;
    }

    public double getBetAmount() {
        return betAmount;
    }
}
