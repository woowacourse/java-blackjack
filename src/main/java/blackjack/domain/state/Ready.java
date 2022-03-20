package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.BlackjackMatch;

public class Ready extends Started {

    private static final String ERROR_MESSAGE_CANNOT_MOVE_TO_STAY = "올바르지 않은 결과입니다.";
    private static final String ERROR_MESSAGE_CANNOT_PROFIT_RATE = "수익률을 확인할 수 없습니다.";
    private static final String ERROR_MESSAGE_CANNOT_SHOW_MATCH = "결과를 확인할 수 없는 상태입니다.";

    public Ready(Cards cards) {
        super(cards);
    }

    public Ready() {
        this(new Cards());
    }

    @Override
    public final State draw(Card card) {
        final Cards cards = getCards();
        cards.receiveCard(card);
        if (cards.isReady()) {
            return new Ready(cards);
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public final State stay() {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_MOVE_TO_STAY);
    }

    @Override
    public BlackjackMatch showMatch(State state) {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_SHOW_MATCH);
    }

    @Override
    public double profitRate(BlackjackMatch blackjackMatch) {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_PROFIT_RATE);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final boolean isRunning() {
        return false;
    }

    @Override
    public final boolean isBust() {
        return false;
    }
}
