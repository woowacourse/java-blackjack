package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final Name name;
    private final Hand hand;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.hand = new Hand(cards);
    }

    public void drawCard(final Deck deck) {
        hand.add(deck.draw());
    }

    public int getScore() {
        return hand.getScore();
    }

    public abstract boolean isHit();

    public String getName() {
        return name.getValue();
    }

    public List<Card> getHand() {
        return hand.getCards();
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
