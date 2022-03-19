package blackjack.model;

import blackjack.model.state.State;

public enum Profits {

    BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    ;

    private final double profit;

    Profits(double profit) {
        this.profit = profit;
    }

    public static Profits compareTo(State dealerState, State playerState) {
        if (dealerState.getScore() > playerState.getScore()) {
            return WIN;
        }
        if (dealerState.getScore() < playerState.getScore()) {
            return LOSE;
        }
        return DRAW;
    }

    public double getProfit() {
        return profit;
    }
}
