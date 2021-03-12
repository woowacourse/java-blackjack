package blackjack.domain.state.running;

import blackjack.domain.Response;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.hitstrategy.HitStrategy;

public class Hit extends Running {

    private final HitStrategy hitStrategy;

    public Hit(Hand hand, HitStrategy hitStrategy) {
        super(hand);
        this.hitStrategy = hitStrategy;
    }

    @Override
    public State draw(Card card) {
        getHand().addCard(card);
        return hitStrategy.moveToState(getHand());
    }

    @Override
    public State moveStateByResponse(Response response) {
        if (response.isNegative()) {
            return new Stay(getHand());
        }
        return this;
    }
}
