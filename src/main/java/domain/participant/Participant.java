package domain.participant;

import static domain.card.Hand.BLACKJACK_SCORE;

import java.util.Objects;

import domain.card.Card;
import domain.card.Hand;
import domain.participant.attributes.Name;

public abstract class Participant {

    private final Name name;
    private final Hand hand;

    public Participant(final Name name) {
        this.name = name;
        hand = new Hand();
    }

    public void receive(final Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return score() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return score() == BLACKJACK_SCORE;
    }

    public Name name() {
        return name;
    }

    public Hand hand() {
        return hand;
    }

    public abstract boolean canHit();

    public abstract int score();

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return (object instanceof Participant other) && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
