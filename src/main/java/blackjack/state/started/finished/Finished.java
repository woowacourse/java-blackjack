package blackjack.state.started.finished;

import blackjack.card.Card;
import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.Started;
import java.math.BigDecimal;

public abstract class Finished extends Started {
    public Finished(Hand hand) {
        super(hand);
    }

    @Override
    public State hit(Card card) {
        throw new IllegalStateException("[ERROR] 차례가 끝나 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 이미 차례가 끝났습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public BigDecimal getProfit(BigDecimal bettingAmount) {
        return bettingAmount.multiply(BigDecimal.valueOf(getProfitRate()));
    }

    public abstract double getProfitRate();
}
