package blackjack_statepattern.state;

import blackjack_statepattern.Card;
import blackjack_statepattern.Cards;

public class Blackjack implements State {

    private final Cards cards;

    Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(final Card card) {
        throw new IllegalArgumentException("더이상 진행할 수 없습니다.");
    }
}
