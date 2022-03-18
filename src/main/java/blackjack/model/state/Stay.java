package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class Stay implements State {

    private final Cards cards;

    protected Stay(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(Card card) {
        throw new IllegalArgumentException("[ERROR] Stay상태 이기 때문에 카드를 추가 할 수 없습니다.");
    }

    @Override
    public boolean isReady() {
        return false;
    }
}
