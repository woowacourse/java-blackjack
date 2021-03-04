package blackjack.domain;

import java.util.ArrayList;

public abstract class User {
    private static final int BUST_VALUE = 21;

    protected final Cards cards;
    protected final Name name;

    public User(String name) {
        this(new Name(name));
    }

    public User(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public void receiveCards(Cards cards) {
        this.cards.combine(cards);
    }

    public boolean isNotBust() {
        return cards.calculateTotalValue() <= BUST_VALUE;
    }

    public void hit() {
        this.cards.combine(Deck.popOne());
    }

    public Cards showCards() {
        return this.cards;
    }

    public String getName() {
        return this.name.getName();
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
