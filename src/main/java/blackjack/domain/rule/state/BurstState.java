package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class BurstState extends State {

    BurstState(final Hands hands) {
        super(hands, 0);
    }

    BurstState(final Hands hands, final int hitCount) {
        super(hands, hitCount);
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
        return new BurstState(hands, hitCount);
    }

    @Override
    public BetLeverage calculateBetLeverage(final State other) {
        return BetLeverage.LOSE;
    }
}
