package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Ready extends Started {

    private static final String ERROR_MESSAGE_CANNOT_MOVE_TO_STAY = "올바르지 않은 결과입니다.";

    public Ready(Cards cards) {
        super(cards);
    }

    public Ready() {
        this(new Cards());
    }

    @Override
    public final Status draw(Card card) {
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
    public final Status stay() {
        throw new IllegalArgumentException(ERROR_MESSAGE_CANNOT_MOVE_TO_STAY);
    }
}
