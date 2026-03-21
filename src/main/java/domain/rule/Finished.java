package domain.rule;

import static exception.ErrorMessage.GAME_IS_FINISHED;

import domain.card.Card;
import domain.card.Cards;

public abstract class Finished extends Started {
    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException(GAME_IS_FINISHED.getMessage());
    }

    @Override
    public State stay() {
        throw new IllegalStateException(GAME_IS_FINISHED.getMessage());
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double betAmount, State dealerState) {
        return betAmount * earningRate(dealerState);
    }

    public abstract double earningRate(State dealerState);
}
