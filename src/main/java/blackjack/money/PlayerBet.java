package blackjack.money;

import blackjack.game.MatchResult;

public class PlayerBet {

    private final String name;
    private final BetMoney betMoney;

    public PlayerBet(String name, BetMoney betMoney) {
        this.name = name;
        this.betMoney = betMoney;
    }

    public int calculateProfit(MatchResult matchResult) {
        int money = betMoney.getAmount();
        return matchResult.calculateProfit(money);
    }
}
