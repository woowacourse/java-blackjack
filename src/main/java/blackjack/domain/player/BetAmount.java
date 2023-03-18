package blackjack.domain.player;

public class BetAmount {
    private static final int MIN_AMOUNT = 1000;
    private static final int MIN_UNIT = 10;

    private final int betAmount;

    public BetAmount(int betAmount) {
        validate(betAmount);
        this.betAmount = betAmount;
    }

    public int calculateProfit(double ratio) {
        return (int) (this.betAmount * ratio);
    }

    private void validate(int value) {
        validateMinAmount(value);
        validateAmountUnit(value);
    }

    private void validateMinAmount(int value) {
        if (value < MIN_AMOUNT) {
            throw new IllegalArgumentException("배팅 최소 금액은 " + MIN_AMOUNT + "원 입니다.");
        }
    }

    private void validateAmountUnit(int value) {
        if (value % 10 != 0) {
            throw new IllegalArgumentException("배팅 금액의 단위는 " + MIN_UNIT + "원 입니다.");
        }
    }
}
