package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public final class PlayerHitState extends State {

    public PlayerHitState(final Hands hands) {
        super(hands, 0);
    }

    public PlayerHitState(final Hands hands, final int hitCount) {
        super(hands, hitCount);
    }

    public static State start(final Card first, final Card second) {
        final Hands hands = new Hands(List.of(first, second));

        if (hands.calculateScore().isBlackjack()) {
            return new BlackjackState(hands);
        }

        return new PlayerHitState(hands);
    }

    @Override
    public boolean isHit() {
        return true;
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);

        if (newHands.calculateScore().isBurst()) {
            return new BurstState(newHands, hitCount + 1);
        }

        return new PlayerHitState(newHands, hitCount + 1);
    }

    @Override
    public StandState stand() {
        return new StandState(hands, hitCount);
    }

    @Override
    public BetLeverage calculateBetLeverage(final State other) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }
}
