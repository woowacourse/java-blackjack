package blackjack.domain.user.state;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;
import blackjack.domain.user.Hand;

public abstract class Running extends State {

    protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final Money calculateProfit(Money bettingMoney, State opponentState) {
        throw new IllegalStateException("점수를 계산할 수 없는 상태입니다.");
    }

    public abstract State hit(Card card);
}
