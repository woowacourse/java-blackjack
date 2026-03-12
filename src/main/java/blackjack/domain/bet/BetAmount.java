package blackjack.domain.bet;

public class BetAmount {
    private final int amount;

    public BetAmount(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private void validatePositive(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("베팅 금액은 1원 이상이여야 합니다.");
        }
    }

    public int calculateProfit(double payoutRate) {
        return (int) (amount * payoutRate);
    }
}
