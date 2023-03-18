package domain.people;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.Hand;

public final class Participant {
    private final Hand hand;
    private final String name;

    public Participant(final List<Card> cards, final String name) {
        this.hand = new Hand(cards);
        this.name = name;
    }

    public void receiveCard(final Card card) {
        hand.addCard(card);
    }

    public List<Card> fetchHand() {
        return hand.getHand();
    }

    public int fetchHandValue() {
        return hand.calculateHandValue();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Participant that = (Participant)o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand, name);
    }
}
