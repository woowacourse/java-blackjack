package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Blackjack implements Status {

    private static final String ERROR_MESSAGE_INVALID_DRAW = "카드를 받을 수 없습니다.";

    private final Cards cards;

    public Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Status draw(Card card) {
        throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_DRAW);
    }

    public Cards getCards() {
        return cards;
    }
}
