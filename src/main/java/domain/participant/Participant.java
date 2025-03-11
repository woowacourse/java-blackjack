package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.Objects;

public abstract class Participant {

    private final Hand hand;

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getCardScore() {
        return hand.calculateFinalScore();
    }

    public boolean isBurst() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public Hand getCards() {
        return hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(hand, that.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand);
    }
}
