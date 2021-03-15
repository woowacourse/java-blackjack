package blackjack.domain.player;

import blackjack.domain.game.WinOrLose;

public class Gambler extends Player {

    public Gambler(String name, BettingMoney bettingMoney) {
        super(name, bettingMoney);
    }

    public void calculateProfit(WinOrLose winOrLose) {
        bettingMoney = winOrLose.calculateProfit(bettingMoney);
    }

    public BettingMoney inverseMoney() {
        return bettingMoney.inverseMoney();
    }
}
