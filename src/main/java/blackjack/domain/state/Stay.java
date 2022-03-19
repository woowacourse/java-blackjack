package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Stay implements State {
    private final Cards cards;

    public Stay(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("Stay 상태에선 카드를 뽑을 수 없습니다.");
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public State stay() {
        throw new IllegalStateException("이미 Stay 상태입니다.");
    }
}
