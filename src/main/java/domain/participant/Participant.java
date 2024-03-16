package domain.participant;

import domain.card.Card;
import domain.card.Hands;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BLACK_JACK_COUNT = 21;
    public static final int BLACKJACK_HAND_COUNT = 2;

    protected final Hands hands;
    private final Name name;

    protected Participant(final Name name) {
        this.hands = new Hands();
        this.name = name;
    }

    public abstract boolean canHit();

    public abstract List<Card> revealCardOnInitDeal();

    public void receiveCard(Card card) {
        hands.receive(card);
    }

    public void receiveCard(List<Card> cards) {
        hands.receive(cards);
    }

    public boolean isBust() {
        return hands.calculateScore() > BLACK_JACK_COUNT;
    }

    public boolean isBlackJack() {
        return hands.getCardCount() == BLACKJACK_HAND_COUNT && hands.calculateScore() == BLACK_JACK_COUNT;
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
