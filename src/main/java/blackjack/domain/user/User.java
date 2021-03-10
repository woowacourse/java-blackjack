package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {
    protected final Cards cards;
    protected final Name name;

    public User(String name) {
        this(new Name(name));
    }

    public User(Name name) {
        this(new ArrayList<>(), name);
    }

    public User(List<Card> cards, String name) {
        this(new Cards(cards), new Name(name));
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

    public final Score score() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(cards, user.cards) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, name);
    }
}
