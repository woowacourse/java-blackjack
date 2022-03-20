package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

import java.util.List;

public abstract class State {
    final protected PrivateArea privateArea;
    final protected Chip chip;

    protected State(final PrivateArea privateArea, final Chip chip) {
        this.privateArea = privateArea;
        this.chip = chip;
    }

    public abstract State draw(Card card);

    public abstract State stay();

    public abstract State compare(State dealerState);

    public abstract int getProfit();

    public final boolean isBust() {
        return privateArea.isBust();
    }

    public final boolean isBlackjack() {
        return privateArea.isBlackjack();
    }

    public final int getTotalScore() {
        return privateArea.getTotalScore();
    }

    public final Chip getChip() {
        return chip;
    }

    public final String getName() {
        return privateArea.getName();
    }

    public final List<Card> getCards() {
        return privateArea.getCards();
    }
}
