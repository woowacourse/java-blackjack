package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Stay implements State{
    private static final String STAY_ADD_CARD_ERROR_MESSAGE = "스테이일 때에는 카드를 받을 수 없습니다.";
    private final Cards cards;

    public Stay(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State addCard(Card card) {
        throw new UnsupportedOperationException(STAY_ADD_CARD_ERROR_MESSAGE);
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException();
    }
}
