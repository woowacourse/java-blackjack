package blackjack.domain.betting;

import blackjack.domain.process.Match;

public class Profit {
    private final Match match;
    private final int bettingMoney;

    private Profit(Match match, int bettingMoney) {
        this.match = match;
        this.bettingMoney = bettingMoney;
    }

    public static Profit of(Match match, int bettingMoney) {
        return new Profit(match, bettingMoney);
    }

    public static Profit from(int bettingMoney) {
        return new Profit(Match.LOSE, bettingMoney);
    }

    public int calculateProfit() {
        return (int) (this.match.getRatio() * this.bettingMoney);
    }
}
