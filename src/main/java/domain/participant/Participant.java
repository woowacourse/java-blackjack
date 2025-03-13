package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;

public abstract class Participant {
    protected final Hand hand;

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public abstract Hand openInitialHand();

    public Hand getCards() {
        return hand;
    }

    public void addCards(Hand receivedHand) {
        hand.addAll(receivedHand);
    }

    public void addCard(Card receivedCard) {
        hand.add(receivedCard);
    }

    public Score getScore() {
        return Score.from(hand);
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public void initializeHand(Hand hand) {
        this.addCards(hand);
    }
}
