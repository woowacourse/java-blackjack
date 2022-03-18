package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Bust implements State {

    private final Cards cards;

    public Bust(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State hit(Card card) {
        throw new UnsupportedOperationException("[ERROR] 게임을 진행할 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("[ERROR] 게임을 진행할 수 없습니다.");
    }
}
