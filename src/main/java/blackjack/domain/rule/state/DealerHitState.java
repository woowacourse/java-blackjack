package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.Score;
import java.util.List;

public final class DealerHitState extends State {

    public static final Score NEED_CARD_SCORE_MAX = Score.from(16);

    DealerHitState(final Hands hands) {
        super(hands);
    }

    public static State start(final Card first, final Card second) {
        final Hands hands = new Hands(List.of(first, second));

        if (hands.isBlackjackScore()) {
            return new BlackjackState(hands);
        }

        if (needMoreCard(hands)) {
            return new DealerHitState(hands);
        }

        return new StandState(hands);
    }

    private static boolean needMoreCard(final Hands hands) {
        final Score score = hands.calculateScore();

        return score.compareTo(NEED_CARD_SCORE_MAX) <= 0;
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        if (newHands.isBustScore()) {
            return new BustState(newHands);
        }
        if (needMoreCard(newHands)) {
            return new DealerHitState(newHands);
        }
        return new StandState(newHands);
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public BetLeverage calculateBetLeverage(final State other) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public boolean isHit() {
        return true;
    }
}
