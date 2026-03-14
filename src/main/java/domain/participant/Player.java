package domain.participant;

import domain.game.Outcome;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean canHit() {
        return !isBust() && !isBlackjack();
    }

    public int calculateProfit(Outcome outcome) {
        return bettingMoney.calculateProfit(outcome);
    }
}
