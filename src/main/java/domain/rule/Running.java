package domain.rule;

import static exception.ErrorMessage.GAME_IS_STILL_RUNNING;

import domain.card.Card;
import domain.card.Cards;

public abstract class Running extends Started {
    public Running(Cards cards) {
        super(cards);
    }

    public abstract State draw(Card card);

    public abstract State stay();

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
        return false;
    }

    @Override
    public double profit(double betAmount, State dealerState) {
        throw new IllegalStateException(GAME_IS_STILL_RUNNING.getMessage());
    }
}
