package domain.betting;

public class Profit {
    private final String name;
    private final Money money;
    private final int earningRate;

    public Profit(String name, Money money, int earningRate) {
        this.name = name;
        this.money = money;
        this.earningRate = earningRate;
    }

    public String getName() {
        return name;
    }

    public long calculateProfit() {
        return (earningRate * money.getValue()) / 100;
    }
}
