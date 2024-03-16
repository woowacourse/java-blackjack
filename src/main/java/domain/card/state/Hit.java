package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public class Hit extends Initial {
    protected Hit(Cards cards) {
        super(cards);
    }

    @Override
    public CardState receive(Card card) {
        cards().addCard(card);
        if (cards().isBust()) {
            return new Bust(cards());
        }
        return this;
    }

    @Override
    public CardState finish() {
        return new Stay(cards());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int calculateProfit(int bettingAmount) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " 상태에서는 수익을 계산할 수 없습니다.");
    }
}
