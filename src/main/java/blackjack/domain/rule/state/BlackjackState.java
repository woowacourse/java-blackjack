package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class BlackjackState implements State {

    private final Hands hands;
    private final int hitCount;

    public BlackjackState(final Hands hands) {
        this.hands = hands;
        this.hitCount = 0;
    }

    public BlackjackState(final Hands hands, final int hitCount) {
        this.hands = hands;
        this.hitCount = hitCount;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);
        return new BurstState(newHands, hitCount + 1);
    }

    @Override
    public State stand() {
        return new BlackjackState(hands, hitCount);
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
