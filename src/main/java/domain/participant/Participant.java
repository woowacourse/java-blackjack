package domain.participant;

import domain.card.Card;
import domain.card.Hands;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final int BLACK_JACK_COUNT = 21;

    private final Hands hands;
    private final Name name;

    protected Participant(final Name name) {
        this.name = name;
        this.hands = new Hands();
    }

    public boolean canHit() {
        return hands.calculateScore() <= getThreshold();
    }

    abstract int getThreshold();

    public void receiveCard(Card card) {
        hands.receive(card);
    }

    public boolean isBust() {
        return hands.calculateScore() > BLACK_JACK_COUNT;
    }

    public boolean isBlackJack() {
        return hands.isBlackJack();
    }

    public int getCardCount() {
        return hands.getCardCount();
    }

    public Name getName() {
        return name;
    }

    public int getScore() {
        return hands.calculateScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hands.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
