package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public class Prepare extends Started {
    private static final int INIT_CARD_COUNT = 2;

    public Prepare(Cards cards) {
        super(cards);
    }

    @Override
    public CardState receive(Card card) {
        cards().addCard(card);
        if (cards().isBlackjack()) {
            return new Blackjack(cards());
        }
        if (cards().size() >= INIT_CARD_COUNT) {
            return new Hit(cards());
        }
        return this;
    }

    @Override
    public CardState finish() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " 상태에서는 게임을 종료할 수 없습니다.");
    }
}
