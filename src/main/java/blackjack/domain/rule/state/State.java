package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public abstract class State {

    public static final int START_CARD_COUNT = 2;
    protected static final String ERROR_MESSAGE = "사용할 수 없는 기능입니다.";

    protected final Hands hands;

    protected State(final Hands hands) {
        this.hands = hands;
    }

    public abstract State draw(Card card);

    public abstract State stand();

    public abstract BetLeverage calculateBetLeverage(State other);

    public boolean isHit() {
        return false;
    }

    public boolean isBlackjack() {
        return false;
    }

    public boolean isBust() {
        return false;
    }

    public Hands getHands() {
        return hands;
    }
}
