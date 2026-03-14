package domain.participant;

import domain.bet.BettingMoney;
import domain.game.Result;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean canHit() {
        return !isBust() && !isBlackjack();
    }

    public int calculateProfit(Result result) {
        return bettingMoney.calculateProfit(result);
    }
}
