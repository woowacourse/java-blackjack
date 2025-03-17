package blackjack.domain;

public record BettingAmount(
    double initial,
    double end
) {

    public static BettingAmount of(double money) {
        return new BettingAmount(money, 0);
    }

    public BettingAmount blackjack() {
        return new BettingAmount(initial, initial * RoundResult.BLACKJACK.getBettingAmountMultiple());
    }

    public BettingAmount draw() {
        return new BettingAmount(initial, initial);
    }

    public BettingAmount win() {
        return new BettingAmount(initial, initial * RoundResult.WIN.getBettingAmountMultiple());
    }

    public double getProfit() {
        return end - initial;
    }

    public BettingAmount tie() {
        return new BettingAmount(initial, initial);
    }
}
