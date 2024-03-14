package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class BlackjackState extends State {

    BlackjackState(final Hands hands) {
        super(hands);
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        return new PlayerHitState(newHands);
    }

    @Override
    public State stand() {
        return new BlackjackState(hands);
    }

    @Override
    public BetLeverage calculateBetLeverage(final State other) {
        if (other.isBlackjack()) {
            return BetLeverage.PUSH;
        }
        return BetLeverage.BLACKJACK;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
