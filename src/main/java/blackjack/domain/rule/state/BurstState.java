package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class BurstState implements State {

    private final Hands hands;
    private final int hitCount;

    public BurstState(final Hands hands) {
        this.hands = hands;
        this.hitCount = 0;
    }

    public BurstState(final Hands hands, final int hitCount) {
        this.hands = hands;
        this.hitCount = hitCount;
    }

    @Override
    public boolean isBurst() {
        return true;
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

    @Override
    public int countHit() {
        return hitCount;
    }
}
