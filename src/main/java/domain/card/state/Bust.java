package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public class Bust implements CardState {
    private final Cards cards;

    public Bust(Cards cards) {
        this.cards = cards;
    }

    @Override
    public CardState receive(Card card) {
        throw new UnsupportedOperationException("버스트 상태에서는 카드를 추가할 수 없습니다.");
    }
}
