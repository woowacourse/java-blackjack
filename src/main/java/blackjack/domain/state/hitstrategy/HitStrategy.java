package blackjack.domain.state.hitstrategy;

import blackjack.domain.cards.Hand;
import blackjack.domain.state.State;

public interface HitStrategy {

    State moveToState(Hand hand);
}
