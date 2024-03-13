package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Player {
    static final int BUST_CONDITION = 21;

    protected final Hand hand;
    private final Name name;

    public Player(final String name) {
        this.hand = new Hand();
        this.name = new Name(name);
    }

    public final void addCard(final Card card) {
        hand.add(card);
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

    public final String getName() {
        return name.getValue();
    }
}
