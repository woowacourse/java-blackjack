package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class Burst implements State {

    private final Hands hands;

    public Burst(final Hands hands) {
        this.hands = hands;
    }

    @Override
    public State start(final Card first, final Card second) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public Hands getHands() {
        return new Hands(hands.getCards());
    }
}
