package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.Score;

public final class StandState extends State {

    public StandState(final Hands hands) {
        super(hands, 0);
    }

    public StandState(final Hands hands, final int hitCount) {
        super(hands, hitCount);
    }

    public static State from(final Hands hands) {
        final Score score = hands.calculateScore();

        if (score.isBurst()) {
            return new BurstState(hands);
        }
        if (score.isBlackjack() && hands.isSizeOf(State.START_CARD_COUNT)) {
            return new BlackjackState(hands);
        }
        return new StandState(hands);
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
    public BetLeverage calculateBetLeverage(final State other) {
        if (other.isBurst()) {
            return BetLeverage.WIN;
        }
        if (other.isBlackjack() || other.getHands().calculateScore().isBiggerThan(hands.calculateScore())) {
            return BetLeverage.LOSE;
        }
        if (hands.calculateScore().isBiggerThan(other.getHands().calculateScore())) {
            return BetLeverage.WIN;
        }
        return BetLeverage.DRAW;

    }
}
