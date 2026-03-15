package domain.betting;

public class Profit {
    private final String name;
    private final Money money;
    private final BettingResult bettingResult;

    public Profit(String name, Money money, BettingResult bettingResult) {
        this.name = name;
        this.money = money;
        this.bettingResult = bettingResult;
    }

    public String getName() {
        return name;
    }

    public long calculateProfit() {
        return bettingResult.getEarningRate() * money.getValue() / 100;
    }
}
