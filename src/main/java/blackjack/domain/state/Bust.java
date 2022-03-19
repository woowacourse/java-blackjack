package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Bust implements State {

    private final Cards cards;

    public Bust(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("Bust 상태에선 카드를 받을 수 없습니다.");
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public State stay() {
        throw new IllegalStateException("Bust 상태에선 Stay를 할 수 없습니다.");
    }
}
