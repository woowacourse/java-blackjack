package domain.user;

import domain.Outcome;
import util.BlackJackRule;

public class Player extends User {

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean canDrawCard() {
        return !(cards.isBust() || cards.isBlackJack());
    }

    public Outcome calculateOutcome(int dealerScore) {
        if (cards.isBust()) {
            return Outcome.LOSE;
        }
        if (BlackJackRule.isBust(dealerScore)) {
            return Outcome.WIN;
        }
        return Outcome.calculate(dealerScore, cards.getScore());
    }

    public String getName() {
        return name;
    }
}
