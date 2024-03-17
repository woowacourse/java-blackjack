package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class Participant {
    static final int BUST_CONDITION = 21;

    protected final Hand hand;
    private final Name name;

    protected Participant(final Hand hand, final Name name) {
        this.hand = hand;
        this.name = name;
    }

    public final void addCard(final Card card) {
        hand.add(card);
    }

    public final boolean isAlive() {
        return hand.getScore() <= BUST_CONDITION;
    }

    public boolean isBlackjack() {
        return hand.hasExactlyTwoCards() && hand.getScore() == BUST_CONDITION;
    }

    public final int score() {
        return hand.getScore();
    }

    public final List<Card> getCards() {
        return hand.getAllCards();
    }

    public final Name name() {
        return name;
    }
}
