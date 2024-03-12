package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class StandState implements State {

    private final Hands hands;
    private final int hitCount;

    public StandState(final Hands hands) {
        this.hands = hands;
        this.hitCount = 0;
    }

    public StandState(final Hands hands, final int hitCount) {
        this.hands = hands;
        this.hitCount = hitCount;
    }

    public static State from(final Hands hands) {
        if (hands.calculateScore().isDead()) {
            return new BurstState(hands);
        }
        if (hands.calculateScore().isBlackjack() && hands.isSizeOf(State.START_CARD_COUNT)) {
            return new BlackjackState(hands);
        }

        return new StandState(hands);
    }

    @Override
    public boolean isStand() {
        return true;
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public StandState stand() {
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
