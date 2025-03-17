package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.winning.WinningResult;

public class Blackjack extends Finished {
    public Blackjack(Cards cards) {
        super(cards);
        if (!cards.isBlackjack()) {
            throw new IllegalArgumentException("블랙잭이 아닙니다.");
        }
    }

    @Override
    public WinningResult decide(Cards dealerCards) {
        if (dealerCards.isBlackjack()) {
            return WinningResult.DRAW;
        }
        return WinningResult.WIN;
    }

    @Override
    protected double earningsRate() {
        return 2.5;
    }
}
