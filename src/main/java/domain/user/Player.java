package domain.user;

import domain.Outcome;
import util.BlackJackRule;

public class Player extends User {

    private final String name;
    private Outcome outcome = Outcome.UNDEFINED;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean canDrawCard() {
        return !cards.isBust() && !cards.isBlackJack();
    }

    public void calculateWinningResult(int dealerScore) {
        if (cards.isBust()) {
            outcome = Outcome.LOSE;
            return;
        }
        if (BlackJackRule.isBust(dealerScore) && outcome == Outcome.UNDEFINED) {
            outcome = Outcome.WIN;
            return;
        }
        outcome = Outcome.calculate(dealerScore, cards.getScore());
    }

    public String getName() {
        return name;
    }

    public Outcome getOutcome() {
        return outcome;
    }
}
