package domain.participant;

import domain.Score;
import domain.card.Card;

public class Dealer extends Participant {

    public Card getFirstCard() {
        return hand.peek();
    }

    public boolean isHittable(Score boundary) {
        return getTotalSum().isLessThanOrEqualTo(boundary);
    }
}
