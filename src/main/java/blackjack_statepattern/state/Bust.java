package blackjack_statepattern.state;

import blackjack_statepattern.Card;
import blackjack_statepattern.Cards;

public class Bust implements State {
    private final Cards cards;

    Bust(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalArgumentException("버스트일 때 카드를 받을 수 없습니다.");
    }
}
