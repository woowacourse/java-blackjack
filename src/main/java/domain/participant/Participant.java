package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.Objects;

public class Participant {

    protected final Name name;
    protected final Cards hand;

    public Participant(final Name name) {
        this.name = name;
        hand = new Cards();
    }

    public void receive(final Card card) {
        hand.add(card);
    }

    public int cardSum() {
        int total = hand.sum();
        if (hasAceAsEleven(total)) {
            return total + 10;
        }
        return total;
    }

    private boolean hasAceAsEleven(final int total) {
        return hand.hasAce() && total + 10 <= 21;
    }

    public boolean isBust() {
        return hand.sum() > 21;
    }

    public boolean isBlackjack() {
        return hand.sum() == 21;
    }

    public Name name() {
        return name;
    }

    public Cards hand() {
        return hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(hand, that.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }
}
