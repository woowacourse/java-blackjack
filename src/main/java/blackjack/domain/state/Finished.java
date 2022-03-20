package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.BlackjackMatch;

public abstract class Finished extends Started {

    private static final String ERROR_MESSAGE_INVALID_DRAW = "카드를 받을 수 없습니다.";
    private static final String ERROR_MESSAGE_CANNOT_MOVE_TO_STAY = "올바르지 않은 결과입니다.";

    protected Finished(Cards cards) {
        super(cards);
    }

    public abstract BlackjackMatch showMatch(State state);

    public abstract double profitRate(BlackjackMatch blackjackMatch);

    @Override
    public final State draw(Card card) {
        throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_DRAW);
    }

    @Override
    public final State stay() {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_MOVE_TO_STAY);
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public final boolean isRunning() {
        return false;
    }
}
