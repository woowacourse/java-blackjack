package blackjack.domain.user;

public class WinningResult {
    private final String name;
    private final int profit;

    public WinningResult(String name, int money, MatchResult matchResult) {
        this.name = name;
        this.profit = calculateProfit(money, matchResult);
    }

    public int calculateProfit(int money, MatchResult matchResult) {
        return (int) (money * matchResult.getMoneyRate());
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
