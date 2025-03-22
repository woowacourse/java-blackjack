package blackjack.state.started.ready;

import blackjack.card.Card;
import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.Started;
import java.math.BigDecimal;

public abstract class Ready extends Started {

    public Ready(Hand hand) {
        super(hand);
    }

    @Override
    public State hit(Card card) {
        throw new IllegalStateException("[ERROR] 아직 게임 시작 준비가 되지 않았습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 아직 게임 시작 준비가 되지 않았습니다.");
    }


    @Override
    public BigDecimal getProfit(BigDecimal bettingAmount) {
        throw new IllegalStateException("[ERROR] 아직 게임이 끝나지 않아 수익을 구할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
