package domain.participant;

import domain.Score;
import domain.card.Card;

public class Dealer extends Participant {
    public Dealer(String name) {
        super(name);
    }

    public Card getFirstCard() {
        return hand.peek();
    }

    public boolean decideHitStand(Score boundary) {
        return getTotalSum().isLessThanOrEqualTo(boundary);
    }
}
