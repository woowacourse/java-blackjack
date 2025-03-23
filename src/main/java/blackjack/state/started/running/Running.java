package blackjack.state.started.running;

import blackjack.card.CardDeck;
import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.Started;
import java.math.BigDecimal;

public abstract class Running extends Started {

    public Running(Hand hand) {
        super(hand);
    }

    @Override
    public State initialDeal(CardDeck cardDeck) {
        throw new IllegalStateException("[ERROR] 이미 초기 카드가 배부되었습니다.");
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
