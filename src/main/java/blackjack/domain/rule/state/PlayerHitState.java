package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public final class PlayerHitState extends State {

    PlayerHitState(final Hands hands) {
        super(hands);
    }

    public static State start(final Card first, final Card second) {
        final Hands hands = new Hands(List.of(first, second));

        if (hands.isBlackjackScore()) {
            return new BlackjackState(hands);
        }

        return new PlayerHitState(hands);
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        if (newHands.isBustScore()) {
            return new BustState(newHands);
        }

        return new PlayerHitState(newHands);
    }

    @Override
    public StandState stand() {
        return new StandState(hands);
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
