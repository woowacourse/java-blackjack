package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.user.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public abstract class Finished extends Started {
    protected Finished(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException("[ERROR] 게임이 끝난 상태에서는 카드를 추가로 받을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("[ERROR] 이미 게임이 끝난 상태입니다.");
    }

    @Override
    public final boolean isFinish() {
        return true;
    }

    @Override
    public final long getProfit(Dealer dealer, Money money) {
        return (long) (money.getValue() * earningRate(dealer));
    }
}
