package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished implements Status {

    private static final String ERROR_MESSAGE_INVALID_DRAW = "카드를 받을 수 없습니다.";
    private static final String ERROR_MESSAGE_CANNOT_MOVE_TO_STAY = "올바르지 않은 결과입니다.";

    private Cards cards;

    protected Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public final Status draw(Card card) {
        throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_DRAW);
    }

    @Override
    public final Status stay() {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_MOVE_TO_STAY);
    }

    public Cards getCards() {
        return cards;
    }
}
