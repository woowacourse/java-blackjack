package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished implements State {

    private final Cards cards;

    Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException("더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException();
    }

    @Override
    public final Cards cards() {
        return cards;
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public final double profit(int money) {
        return money * earningRate();
    }

    protected abstract double earningRate();
}
