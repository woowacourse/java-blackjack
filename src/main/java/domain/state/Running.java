package domain.state;

import domain.BattleResult;
import domain.Bet;
import domain.Profit;
import domain.card.Cards;

public abstract class Running extends Started {

    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Profit Profit(Bet bet, BattleResult battleResult) {
        return null;
    }
}
