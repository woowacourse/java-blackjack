package domain.batting;

public class Profit {
    private final Money money;
    private final double earningRate;

    public Profit(Money money, double earningRate) {
        this.money = money;
        this.earningRate = earningRate;
    }
}
