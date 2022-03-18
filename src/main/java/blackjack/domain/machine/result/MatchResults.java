package blackjack.domain.machine.result;

public abstract class MatchResults {

    abstract double profitRate();

    final double calculateProfit(int money) {
        return money * profitRate();
    };

}
