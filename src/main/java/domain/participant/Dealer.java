package domain.participant;

import domain.Score;
import domain.card.Card;

public class Dealer extends Participant {
    public Dealer(String name) {
        super(name);
    }

    public Card getFirstCard() {
        return cards.peek();
    }

    public boolean decideHitStand(Score boundary) {
        return cards.getTotalSum().isLessThanOrEqualTo(boundary);
    }
}
