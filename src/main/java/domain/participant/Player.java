package domain.participant;

import domain.game.HandState;
import domain.game.Outcome;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(String name, int amount) {
        super(name);
        this.bettingMoney = new BettingMoney(amount);
    }

    public Outcome compete(HandState dealerState) {
        return getState().versus(dealerState);
    }

    public int calculateProfit(Outcome outcome) {
        return outcome.calculateProfit(bettingMoney.getAmount());
    }
}
