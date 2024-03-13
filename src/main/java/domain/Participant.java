package domain;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final Name name;
    protected final Hand hand;

    Participant(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void dealCards(final List<Card> cards) {
        hand.addAll(cards);
    }

    public void dealCard(final Card card) {
        hand.add(card);
    }

    public int score() {
        return hand.score().toInt();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public abstract boolean canHit();

    public String name() {
        return name.getName();
    }

    public List<Card> hand() {
        return hand.cards();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Participant player = (Participant) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
