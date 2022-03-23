package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public abstract class Finished implements State {

    private final Cards cards;

    Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public final State draw(Card card) {
        throw new UnsupportedOperationException("더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new UnsupportedOperationException("종료 상태에서는 stay를 할 수 없습니다.");
    }

    @Override
    public final Cards cards() {
        return cards;
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    public final double profit(Dealer dealer, int money) {
        return money * earningRate(dealer);
    }

    protected abstract double earningRate(Dealer dealer);
}
