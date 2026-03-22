package domain.participant;

import domain.game.Outcome;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(String name, int amount) {
        super(name);
        this.bettingMoney = new BettingMoney(amount);
    }

    public int calculateProfit(Dealer dealer) {
        Outcome outcome = calculateOutcomeAgainst(dealer);
        return outcome.calculateProfit(bettingMoney.getAmount());
    }
}