package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.BlackjackMatch;

public interface State {

    String ERROR_MESSAGE_CANNOT_MOVE_TO_STAY = "올바르지 않은 결과입니다.";
    String ERROR_MESSAGE_CANNOT_SHOW_MATCH = "결과를 확인할 수 없는 상태입니다.";
    String ERROR_MESSAGE_CANNOT_PROFIT_RATE = "수익률을 확인할 수 없습니다.";

    State draw(Card card);

    default State stay() {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_MOVE_TO_STAY);
    }

    default BlackjackMatch match(State state) {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_SHOW_MATCH);
    }

    default double profitRate(BlackjackMatch blackjackMatch) {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_PROFIT_RATE);
    }

    default boolean isFinished() {
        return false;
    }

    default boolean isRunning() {
        return false;
    }

    default boolean isBust() {
        return false;
    }

    Cards getCards();
}
