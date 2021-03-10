package blackjack.domain.state.hitstrategy;

import blackjack.domain.cards.Hand;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.running.Hit;
import blackjack.domain.state.State;

public class PlayerStrategy implements HitStrategy {

    @Override
    public State moveToState(Hand hand) {
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (hand.isBlackJack()) {
            return new Blackjack(hand);
        }
        return new Hit(hand, this);
    }
}
