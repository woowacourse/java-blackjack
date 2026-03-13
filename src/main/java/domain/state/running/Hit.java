package domain.state.running;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.hitStrategy.HitStrategy;
import domain.state.State;
import domain.state.finished.Bust;
import domain.state.finished.Stay;

public class Hit extends Running {

    public Hit(Hand hand, HitStrategy hitStrategy) {
        super(hand, hitStrategy);
    }

    @Override
    public State drawCard(Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (isFinished()) {
            return new Stay(hand);
        }
        return new Hit(hand, hitStrategy);
    }
}
