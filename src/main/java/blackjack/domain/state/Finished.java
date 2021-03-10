package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.BettingMoney;

public abstract class Finished extends Started{
    private static final String NOT_STAY_ERROR = "[ERROR] 이미 끝이나서 스테이할 수 없습니다.";
    private static final String NOT_DRAW_ERROR = "[ERROR] 이미 끝이나서 카드를 뽑을 수 없습니다.";

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State stay() {
        throw new IllegalStateException(NOT_STAY_ERROR);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException(NOT_DRAW_ERROR);
    }

    @Override
    public double calculateProfit(BettingMoney bettingMoney) {
        return bettingMoney.getBettingMoney() * profitRatio();
    }

    public abstract double profitRatio();
}
