package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.Score;
import java.util.List;

public final class DealerHitState extends State {

    public static final int NEED_CARD_NUMBER_MAX = 16;

    private DealerHitState(final Hands hands) {
        super(hands, 0);
    }

    DealerHitState(final Hands hands, final int hitCount) {
        super(hands, hitCount);
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

    private static boolean needMoreCard(final Hands hands) {
        return hands.calculateScore().isSmallerOrEqual(new Score(NEED_CARD_NUMBER_MAX));
    }

    @Override
    public boolean isHit() {
        return needMoreCard(hands);
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        if (newHands.calculateScore().isBust()) {
            return new BustState(newHands, hitCount + 1);
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
    public BetLeverage calculateBetLeverage(final State other) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }
}
