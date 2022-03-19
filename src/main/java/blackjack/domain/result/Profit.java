package blackjack.domain.result;

import blackjack.domain.player.BetMoney;

public class Profit {

    private static final int STANDARD_RATE = 1;
    private static final double BLACKJACK_RATE = 1.5;
    private static final int DRAW = 0;

    private final double value;

    public Profit(double value) {
        this.value = value;
    }

    public static Profit of(Match match, BetMoney betMoney) {
        return new Profit(calcProfit(match, betMoney));
    }

    private static double calcProfit(Match match, BetMoney betMoney) {
        if (match.isMatchBlackjackWin() || match.isMatchWin()) {
            return winProfit(match, betMoney);
        }
        if (match.isMatchBlackjackLose() || match.isMatchLose()) {
            return loseProfit(match, betMoney);
        }
        return DRAW;
    }

    private static double loseProfit(Match match, BetMoney betMoney) {
        if (match.isMatchBlackjackLose()) {
            return -(betMoney.getValue() * BLACKJACK_RATE);
        }
        return -(betMoney.getValue() * STANDARD_RATE);
    }

    private static double winProfit(Match match, BetMoney betMoney) {
        if (match.isMatchBlackjackWin()) {
            return betMoney.getValue() * BLACKJACK_RATE;
        }
        return betMoney.getValue() * STANDARD_RATE;
    }

    public double getValue() {
        return value;
    }
}
