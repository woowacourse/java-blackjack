package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public class Blackjack implements CardState {
    private final Cards cards;

    public Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public CardState receive(Card card) {
        throw new UnsupportedOperationException("블랙잭 상태에서는 카드를 추가할 수 없습니다.");
    }

    @Override
    public CardState finish() {
        return this;
    }
}
