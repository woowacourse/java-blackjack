package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class HitState extends PlayingState {

    HitState(final Hands hands) {
        super(hands);
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands().addCard(card);

        if (newHands.isBustScore()) {
            return new BustState(newHands);
        }

        return new HitState(newHands);
    }

    @Override
    public StandState stand() {
        return new StandState(hands());
    }

    @Override
    public State copy() {
        return new HitState(hands());
    }

    @Override
    public boolean isHit() {
        return true;
    }
}
