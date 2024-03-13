package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class BlackjackState extends State {

    BlackjackState(final Hands hands) {
        super(hands, 0);
    }

    BlackjackState(final Hands hands, final int hitCount) {
        super(hands, hitCount);
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        return new PlayerHitState(newHands, hitCount + 1);
    }

    @Override
    public State stand() {
        return new BlackjackState(hands, hitCount);
    }

    @Override
    public BetLeverage calculateBetLeverage(final State other) {
        if (other.isBlackjack()) {
            return BetLeverage.TIE;
        }
        return BetLeverage.LUCKY;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
