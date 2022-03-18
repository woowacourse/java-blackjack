package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Blackjack implements State {

    private final Cards cards;

    public Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("블랙잭 상태에선 카드를 받을 수 없습니다.");
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
