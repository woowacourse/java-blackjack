package blackJack.domain.result;

public class BettingAmount {

    private final int bettingAmount;

    public BettingAmount(int bettingAmount) {
        validatePositiveNumber(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    public static BettingAmount newInstanceByString(String input) {
        return new BettingAmount(toInt(input));
    }

    private static int toInt(String bettingAmount) {
        try {
            return Integer.parseInt(bettingAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 정수여야 합니다.");
        }
    }

    private void validatePositiveNumber(int bettingAmount) {
        if (bettingAmount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 양수여야 합니다.");
        }
    }

    public int calculateProfit(MatchResult matchResult) {
        return matchResult.calculateProfit(bettingAmount);
    }
}
