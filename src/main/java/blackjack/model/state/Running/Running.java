package blackjack.model.state.Running;

import blackjack.model.Profits;
import blackjack.model.card.Cards;
import blackjack.model.state.Started.Started;
import blackjack.model.state.State;

public abstract class Running extends Started {

    protected Running() {
        super();
    }

    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public Profits calculateProfit(State otherState) {
        throw new IllegalArgumentException("[ERROR] 아직 카드 분배가 끝나지 않아 수익률을 계산 할 수 없습니다.");
    }
}
