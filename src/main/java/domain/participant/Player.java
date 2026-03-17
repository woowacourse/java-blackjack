package domain.participant;

import domain.game.Outcome;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(String name, int amount) {
        super(name);
        this.bettingMoney = new BettingMoney(amount);
    }

    @Override
    public boolean canHit() {
        return !isBust() && !isBlackjack();
    }

    public int calculateProfit(Outcome outcome) {
        return outcome.calculateProfit(bettingMoney.getAmount());
    }
}
