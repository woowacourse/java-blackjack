package blackjack.domain.machine.result;

public abstract class MatchCalculator {

    abstract double profitRate();

    public final double calculateProfit(double money) {
        return money * profitRate();
    };

}
