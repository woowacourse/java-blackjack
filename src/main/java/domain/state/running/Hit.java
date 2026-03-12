package domain.state.running;

import domain.card.vo.Card;
import domain.hitStrategy.HitStrategy;
import domain.participants.Hand;
import domain.state.State;
import domain.state.finished.Bust;
import domain.state.finished.Stay;

public class Hit extends Running {
    private final HitStrategy hitStrategy;

    public Hit(Hand hand, HitStrategy hitStrategy) {
        super(hand);
        this.hitStrategy = hitStrategy;
    }

    @Override
    public State drawCard(Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (!hitStrategy.canHit(this)) {
            return new Stay(hand);
        }
        return new Hit(hand, hitStrategy);
    }
}
