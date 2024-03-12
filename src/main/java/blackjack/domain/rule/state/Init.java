package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public final class Init implements State {

    @Override
    public State start(final Card first, final Card second) {
        final Hands hands = new Hands(List.of(first, second));

        if (hands.calculateScore().isBlackjack()) {
            return new Blackjack(hands);
        }

        return new Hit(hands);
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
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }
}
