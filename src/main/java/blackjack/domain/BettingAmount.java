package blackjack.domain;

public record BettingAmount(
    double initial,
    double end
) {

    public static BettingAmount of(double money) {
        return new BettingAmount(money, 0);
    }

    public BettingAmount blackjack() {
        return new BettingAmount(initial, initial * 1.5);
    }

    public BettingAmount draw() {
        return new BettingAmount(initial, initial);
    }

    public BettingAmount win() {
        return new BettingAmount(initial, initial * 2);
    }

    public double getProfit() {
        return end - initial;
    }
}
