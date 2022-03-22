package blackjack.domain.user.state;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;
import blackjack.domain.user.Hand;

public abstract class Finished extends State {

    protected Finished(Hand hand) {
        super(hand);
    }

    @Override
    public final State hit(Card card) {
        throw new IllegalStateException("카드를 더이상 뽑을 수 없는 상태입니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public final Money calculateProfit(Money bettingMoney, State opponentState) {
        return bettingMoney.multiply(calculateEarningRate(opponentState).getRate());
    }

    public abstract EarningRate calculateEarningRate(State opponentState);
}
