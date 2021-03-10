package blackjack.domain.state.hitstrategy;

import blackjack.domain.cards.Hand;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.running.Hit;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Stay;

public class DealerStrategy implements HitStrategy {

    private static final int DEALER_LIMIT = 16;

    @Override
    public State moveState(Hand hand) {
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (hand.isBlackJack()) {
            return new Blackjack(hand);
        }
        if (isOverLimit(hand)) {
            return new Stay(hand);
        }
        return new Hit(hand, this);
    }

    private boolean isOverLimit(Hand hand) {
        return hand.getScore() > DEALER_LIMIT;
    }
}
