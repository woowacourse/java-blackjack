package model.betting;

public interface Bettable extends BetOwnable {

    Bet makeBet(int betAmount);
}
