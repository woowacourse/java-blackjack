package participant;

import card.Card;
import hand.Hand;
import participant.value.Score;
import result.GameStatus;

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
        return Score.from(hand.getCards());
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isNotBust() {
        return !hand.isBust();
    }

    public GameStatus calculateResultAgainst(Dealer dealer) {
        return hand.calculateResultAgainst(dealer.hand);
    }
}
