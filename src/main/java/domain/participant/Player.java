package domain.participant;

import domain.result.Outcome;

public class Player extends Participant {
    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    @Override
    public boolean canDraw() {
        return !getScore().isBust() && !getScore().isBlackjack();
    }

    public Money calculateProfit(Outcome outcome) {
        return this.money.multiply(outcome.getRate());
    }
}
