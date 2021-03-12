package blackjack.domain.player;

import blackjack.domain.game.WinOrLose;

public class Gambler extends Player {

    public Gambler(String name, Money money) {
        super(name, money);
    }

    public void calculateProfit(WinOrLose winOrLose) {
        money = winOrLose.calculateProfit(money);
    }

    public Money inverseMoney() {
        return money.inverseMoney();
    }
}
