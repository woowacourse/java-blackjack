package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.Score;
import java.util.List;

public final class DealerHitState implements State {

    public static final int NEED_CARD_NUMBER_MAX = 16;

    private final Hands hands;
    private final int hitCount;

    private DealerHitState(final Hands hands) {
        this.hands = hands;
        this.hitCount = 0;
    }

    public DealerHitState(final Hands hands, final int hitCount) {
        this.hands = hands;
        this.hitCount = hitCount;
    }

    private static boolean needMoreCard(final Hands hands) {
        return hands.calculateScore().isSmallerOrEqual(new Score(NEED_CARD_NUMBER_MAX));
    }

    public static State start(final Card first, final Card second) {
        final Hands hands = new Hands(List.of(first, second));

        if (hands.calculateScore().isBlackjack()) {
            return new BlackjackState(hands);
        }

        if (needMoreCard(hands)) {
            return new DealerHitState(hands);
        }

        return new StandState(hands);
    }

    @Override
    public boolean isHit() {
        return needMoreCard(hands);
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        if (newHands.calculateScore().isDead()) {
            return new BurstState(newHands, hitCount + 1);
        }

        if (needMoreCard(newHands)) {
            return new DealerHitState(newHands, hitCount + 1);
        }

        return new StandState(newHands, hitCount + 1);
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
