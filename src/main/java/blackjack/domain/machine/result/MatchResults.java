package blackjack.domain.machine.result;

public abstract class MatchResults {

    abstract double profitRate();

    public final double calculateProfit(double money) {
        return money * profitRate();
    };

}
