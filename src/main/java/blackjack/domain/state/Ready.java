package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Ready implements Status {

    private static final String ERROR_MESSAGE_CANNOT_MOVE_TO_STAY = "올바르지 않은 결과입니다.";

    private final Cards cards;

    public Ready(Cards cards) {
        this.cards = cards;
    }

    public Ready() {
        this(new Cards());
    }

    @Override
    public Status draw(Card card) {
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
    public Status stay() {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_MOVE_TO_STAY);
    }
}
