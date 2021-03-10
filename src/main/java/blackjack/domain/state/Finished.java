package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.BettingMoney;

public abstract class Finished extends Started{

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 이미 끝이 났습니다.");
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("[ERROR] 이미 끝이나서 카드를 뽑을 수 없습니다.");
    }

    @Override
    public double calculateProfit(BettingMoney bettingMoney) {
        return bettingMoney.getBettingMoney() * profitRatio();
    }

    public abstract double profitRatio();
}
