package domain.state;

import domain.Score;
import domain.card.Hand;

public abstract class Started implements State {
    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public boolean isScoreLessThanOrEqualTo(Score target) {
        return hand.isScoreLessThanOrEqualTo(target);
    }

    @Override
    public Score totalSum() {
        return hand.totalSum();
    }
}
