package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.player.BettingMoney;

public abstract class Running extends Started{

    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double calculateProfit(BettingMoney bettingMoney) {
        throw new IllegalStateException("[ERROR] 게임이 끝나지 않아 수익을 계산할 수 없습니다.");
    }
}
