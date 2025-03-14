package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Blackjack extends Start {
    public Blackjack(Cards cards) {
        super(cards);
        if (!cards.isBlackjack()) {
            throw new IllegalArgumentException("블랙잭이 아닙니다.");
        }
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("블랙잭이므로 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        return this;
    }
}
