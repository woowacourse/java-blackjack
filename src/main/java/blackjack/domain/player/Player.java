package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Player {
    static final int BUST_CONDITION = 21;
    private static final int BLACKJACK_CONDITION = 21;

    protected final Hand hand;
    private final Name name;

    private Player(final Hand hand, final Name name) {
        this.hand = hand;
        this.name = name;
    }

    public Player(final Name name) {
        this(new Hand(), name);
    }

    public final void addCard(final Card card) {
        hand.add(card);
    }

    public boolean isBlackjack() {
        return hand.hasExactlyTwoCards() && hand.getScore() == BLACKJACK_CONDITION;
    }

    public final boolean isAlive() {
        return hand.getScore() <= BUST_CONDITION;
    }

    public final int getScore() {
        return hand.getScore();
    }

    public final List<Card> getCards() {
        return hand.getAllCards();
    }

    public final Name getName() {
        return name;
    }
}
