package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public final class HitState implements State {

    private final Hands hands;
    private final int hitCount;

    public HitState(final Hands hands) {
        this.hands = hands;
        this.hitCount = 0;
    }

    public HitState(final Hands hands, final int hitCount) {
        this.hands = hands;
        this.hitCount = hitCount;
    }

    public static State start(final Card first, final Card second) {
        final Hands hands = new Hands(List.of(first, second));

        if (hands.calculateScore().isBlackjack()) {
            return new BlackjackState(hands);
        }

        return new HitState(hands);
    }

    @Override
    public boolean isHit() {
        return true;
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        if (newHands.calculateScore().isDead()) {
            return new BurstState(newHands, hitCount + 1);
        }

        return new HitState(newHands, hitCount + 1);
    }

    @Override
    public StandState stand() {
        return new StandState(hands, hitCount);
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
