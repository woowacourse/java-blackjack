package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    protected Participant(final String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public final void addCard(final Card card) {
        hand.add(card);
    }

    public final boolean isBust() {
        return hand.isBust();
    }

    public final boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public abstract boolean isDrawable();

    public final int getScore() {
        return hand.calculateScore();
    }

    public final List<Card> getCards() {
        return hand.getAllCards();
    }

    public final String getName() {
        return name.getValue();
    }
}
