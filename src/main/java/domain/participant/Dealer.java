package domain.participant;

import domain.Score;
import domain.card.Card;

public class Dealer extends Participant {
    private Dealer() {
    }

    public static Dealer createReady() {
        return new Dealer();
    }

    public Card getFirstCard() {
        return hand.peek();
    }

    public boolean isHittable(Score boundary) {
        return getTotalSum().isLessThanOrEqualTo(boundary);
    }
}
