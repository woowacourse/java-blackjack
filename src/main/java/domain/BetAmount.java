package domain;

public class BetAmount {

    public static final int MIN_AMOUNT = 1000;
    public static final int MAX_AMOUNT = 1000000;
    private final int betAmount;

    public BetAmount(int betAmount) {
        validateBetAmount(betAmount);
        this.betAmount = betAmount;
    }

    private void validateBetAmount(int betAmount) {
        if (betAmount < MIN_AMOUNT|| betAmount > MAX_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 1,000원에서 1,000,000원 사이여야합니다.");
        }
    }
}
