package domain.state;

import domain.BattleResult;
import domain.Bet;
import domain.Profit;
import domain.card.Card;
import domain.card.Cards;

public abstract class Finished extends Started {

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State hit(Card card) {
        throw new IllegalStateException("hit를 할 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("stay를 외칠 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Profit Profit(Bet bet, BattleResult battleResult) {
        return null;
    }

    abstract double earningRate();
}
