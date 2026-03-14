package domain.player;

import domain.betting.BettingAmount;

public class Gambler extends Participant {

    private final BettingAmount bettingAmount;

    public Gambler(String name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }
}
