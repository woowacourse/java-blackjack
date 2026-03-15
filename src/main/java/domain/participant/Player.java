package domain.participant;

import domain.BettingMoney;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(String name, long bettingMoney) {
        super(new Name(name), new Hand());
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public long calculateBattingProfit(double profit) {
        return bettingMoney.calculateBattingProfit(profit);
    }
}
