package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class Blackjack implements State{

    private final Cards cards;

    protected Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(Card card) {
        throw new IllegalArgumentException("[ERROR] 카드 숫자 합이 Blackjack이므로 카드를 추가 할 수 없습니다.");
    }
}
