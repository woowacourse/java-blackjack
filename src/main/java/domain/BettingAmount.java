package domain;

public class BettingAmount {
    private final Integer bettingAmount;

    public BettingAmount(Integer bettingAmount) {
        validateMinus(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private static void validateMinus(Integer bettingAmount) {
        if (bettingAmount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 없습니다.");
        }
    }
}
