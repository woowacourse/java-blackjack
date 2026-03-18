package domain.participant;

import static domain.result.GameResult.BLACKJACK_SCORE;

import domain.BettingMoney;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(String name, long bettingMoney) {
        super(new Name(name), new Hand());
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    @Override
    public boolean canHit() {
        return getScore() < BLACKJACK_SCORE;
    }

    public long calculateBattingProfit(double profit) {
        return bettingMoney.calculateBattingProfit(profit);
    }
}
