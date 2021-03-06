package blackjack.domain.user;

import blackjack.domain.card.Deck;

import java.util.ArrayList;

public abstract class User {
    protected final Cards cards;
    protected final Name name;

    public User(String name) {
        this(new Name(name));
    }

    public User(Name name) {
        this(new Cards(new ArrayList<>()), name);
    }

    public User(Cards cards, Name name) {
        this.cards = cards;
        this.name = name;
    }

    public void receiveCards(Cards cards) {
        this.cards.combine(cards);
    }

    public boolean isAbleToHit() {
        return !cards.isBust();
    }

    public void hit() {
        this.cards.combine(Deck.popOne());
    }

    public int score() {
        return cards.calculateTotalValue();
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
