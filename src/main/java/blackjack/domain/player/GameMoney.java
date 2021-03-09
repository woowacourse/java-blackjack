package blackjack.domain.player;

import blackjack.domain.BettingMoney;

public class GameMoney {
    private final int startMoney;
    private int endMoney;

    public GameMoney() {
        this.startMoney = 0;
        this.endMoney = 0;
    }

    public GameMoney(BettingMoney bettingMoney) {
        startMoney = bettingMoney.getMoney();
        endMoney = startMoney;
    }

    public int getProfit() {
        return endMoney - startMoney;
    }
}
