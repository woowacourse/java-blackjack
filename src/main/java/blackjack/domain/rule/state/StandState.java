package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class StandState extends State {

    StandState(final Hands hands) {
        super(hands, 0);
    }

    StandState(final Hands hands, final int hitCount) {
        super(hands, hitCount);
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
        if (other.isBust()) {
            return BetLeverage.WIN;
        }
        if (other.isBlackjack() || other.getScore().isBiggerThan(hands.calculateScore())) {
            return BetLeverage.LOSE;
        }
        if (hands.calculateScore().isBiggerThan(other.getScore())) {
            return BetLeverage.WIN;
        }
        return BetLeverage.PUSH;
    }
}
