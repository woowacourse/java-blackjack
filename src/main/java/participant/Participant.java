package participant;

import card.Card;
import card.Hand;
import participant.value.Score;

public abstract class Participant {
    protected final Hand hand;

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public abstract Hand openHand();

    public abstract Participant initializeHandWith(Hand updatedHand);

    public abstract Participant updateHandWith(Card card);

    public Hand getHand() {
        return hand;
    }

    public Score getScore() {
        return Score.from(hand);
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public boolean isNotBust() {
        return !getScore().isBust();
    }

    public boolean isBlackJack() {
        return getScore().isBlackJackValue() && hand.isInitialStatus();
    }
}
