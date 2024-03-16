package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public abstract class Finished implements CardState {
    private final Cards cards;

    public Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public CardState receive(Card card) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " 상태에서는 카드를 추가할 수 없습니다.");
    }

    @Override
    public CardState finish() {
        return this;
    }
}
