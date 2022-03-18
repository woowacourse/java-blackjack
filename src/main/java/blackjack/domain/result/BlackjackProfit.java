package blackjack.domain.result;

import java.util.Objects;

public class BlackjackProfit {

    private static final double BLACKJACK_RATE = 1.5;
    private static final double NORMAL_RATE = 1;

    private final double profit;

    private BlackjackProfit(double profit) {
        this.profit = profit;
    }

    public static BlackjackProfit from(double profit) {
        return new BlackjackProfit(profit);
    }

    public static BlackjackProfit of(BlackjackMatch blackjackMatch, BettingMoney bettingMoney) {
        final int money = bettingMoney.getMoney();
        if (blackjackMatch.isBlackjack()) {
            return new BlackjackProfit(money * calculateWinLose(blackjackMatch) * BLACKJACK_RATE);
        }
        if (blackjackMatch == BlackjackMatch.WIN || blackjackMatch == BlackjackMatch.LOSE) {
            return new BlackjackProfit(money * calculateWinLose(blackjackMatch) * NORMAL_RATE);
        }
        return new BlackjackProfit(0);
    }

    private static int calculateWinLose(BlackjackMatch blackjackMatch) {
        if (blackjackMatch.getResult().equals(BlackjackMatch.WIN.getResult())) {
            return 1;
        }
        return -1;
    }

    public double getProfit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackjackProfit that = (BlackjackProfit) o;
        return Double.compare(that.getProfit(), getProfit()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProfit());
    }
}
