package blackjack.model;

public class Bet {
    private final int betAmount;

    public Bet(int betAmount) {
        validatePositive(betAmount);
        this.betAmount = betAmount;
    }


    private void validatePositive(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("베팅 금액은 1 이상이여야 합니다.");
        }
    }

    public int calculateProfit(double payoutRate) {
        return (int) (betAmount * payoutRate);
    }
}
