package domain.participant;

import domain.card.Card;
import domain.card.Hands;

import java.util.Collections;
import java.util.List;

public class Participant {

    private static final int BLACK_JACK_COUNT = 21;
    private static final int MIN_CARD_COUNT = 2;

    protected Hands hands;
    private final Name name;

    public Participant(final Name name) {
        this.name = name;
        this.hands = new Hands();
    }

    public void receiveCard(Card card) {
        hands.receive(card);
    }

    public boolean canHit() {
        return hands.calculateScore() <= BLACK_JACK_COUNT;
    }

    public boolean isBust() {
        return hands.calculateScore() > BLACK_JACK_COUNT;
    }

    public boolean isBlackJack() {
        return this.getScore() == BLACK_JACK_COUNT && this.getCardCount() == MIN_CARD_COUNT;
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
}
