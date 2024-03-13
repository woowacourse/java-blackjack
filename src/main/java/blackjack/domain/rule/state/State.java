package blackjack.domain.rule.state;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.Score;

public abstract class State {

    public static final int START_CARD_COUNT = 2;
    protected static final String ERROR_MESSAGE = "사용할 수 없는 기능입니다.";

    protected final Hands hands;
    protected final int hitCount;

    protected State(final Hands hands, final int hitCount) {
        this.hands = hands;
        this.hitCount = hitCount;
    }

    abstract State draw(Card card);

    abstract State stand();

    abstract BetLeverage calculateBetLeverage(State other);

    public int countHit() {
        return hitCount;
    }

    public Score getScore() {
        return hands.calculateScore();
    }

    public Hands getHands() {
        return hands;
    }

    public boolean isHit() {
        return false;
    }

    public boolean isBlackjack() {
        return false;
    }

    public boolean isBurst() {
        return false;
    }
}
