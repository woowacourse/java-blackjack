package domain.state;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.hitStrategy.HitStrategy;
import domain.state.finished.BlackJack;
import domain.state.running.Hit;
import java.util.List;

public abstract class Started implements State {
    protected final Hand hand;
    protected final HitStrategy hitStrategy;

    protected Started(Hand hand, HitStrategy hitStrategy) {
        this.hand = hand;
        this.hitStrategy = hitStrategy;
    }

    public static State getStartState(Hand hand, HitStrategy hitStrategy) {
        if (BlackJack.isBlackJack(hand)) {
            return new BlackJack(hand);
        }
        Hit hit = new Hit(hand, hitStrategy);
        if (hit.isFinished()) {
            return hit.stay();
        }
        return hit;
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }
}
