package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class Hit implements State {

    private final Hands hands;

    public Hit(final Hands hands) {
        this.hands = hands;
    }

    @Override
    public State start(final Card first, final Card second) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        if (newHands.calculateScore().isDead()) {
            return new Burst(newHands);
        }

        return new Hit(newHands);
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
