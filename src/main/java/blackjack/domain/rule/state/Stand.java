package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class Stand implements State {

    private final Hands hands;

    public Stand(final Hands hands) {
        this.hands = hands;
    }

    public static State from(final Hands hands) {
        if (hands.calculateScore().isDead()) {
            return new Burst(hands);
        }
        if (hands.calculateScore().isBlackjack() && hands.isSizeOf(State.START_CARD_COUNT)) {
            return new Blackjack(hands);
        }

        return new Stand(hands);
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
    public Stand stand() {
        return new Stand(hands);
    }

    @Override
    public Hands getHands() {
        return new Hands(hands.getCards());
    }
}
