package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.winning.WinningResult;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
        if (!cards.isBust()) {
            throw new IllegalStateException("버스트가 아닙니다.");
        }
    }

    @Override
    protected WinningResult decide(Cards dealerCards) {
        return WinningResult.LOSE;
    }

    @Override
    protected double earningsRate() {
        return 0;
    }
}
