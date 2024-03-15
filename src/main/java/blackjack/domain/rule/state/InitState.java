package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class InitState extends PlayingState {

    public static int START_CARD_COUNT = 2;

    public InitState() {
        this(new Hands());
    }

    InitState(final Hands hands) {
        super(hands);
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands().addCard(card);

        if (newHands.isBlackjackScore()) {
            return new BlackjackState(newHands);
        }
        if (newHands.size() < START_CARD_COUNT) {
            return new InitState(newHands);
        }
        return new HitState(newHands);
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public State copy() {
        return new InitState(hands());
    }
}
