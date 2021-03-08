package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected final Cards cards;
    protected final Name name;

    public User(String name) {
        this(new Name(name));
    }

    public User(Name name) {
        this(new ArrayList<>(), name);
    }

    public User(List<Card> cards, Name name) {
        this(new Cards(cards), name);
    }

    public User(Cards cards, Name name) {
        this.cards = cards;
        this.name = name;
    }

    public final void receiveCards(Cards cards) {
        this.cards.combine(cards);
    }

    public final boolean isAbleToHit() {
        return !cards.isBust();
    }

    public final void hit() {
        cards.combine(Deck.popOne());
    }

    public final int score() {
        return cards.calculateTotalValue();
    }

    public final Cards getCards() {
        return cards;
    }

    public final String getName() {
        return name.getName();
    }

    @Override
    public final String toString() {
        return name.toString();
    }
}
