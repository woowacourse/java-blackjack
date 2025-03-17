package blackjack.domain;

public record BettingAmount(
    double initial,
    double end
) {

    public static final double WINNER_MULTIPLE = 2;
    public static final double BLACKJACK_MULTIPLE = 1.5;

    public static BettingAmount of(double money) {
        return new BettingAmount(money, 0);
    }

    public BettingAmount blackjack() {
        return new BettingAmount(initial, initial * BLACKJACK_MULTIPLE);
    }

    public BettingAmount draw() {
        return new BettingAmount(initial, initial);
    }

    public BettingAmount win() {
        return new BettingAmount(initial, initial * WINNER_MULTIPLE);
    }

    public double getProfit() {
        return end - initial;
    }

    public BettingAmount tie() {
        return new BettingAmount(initial, initial);
    }
}
