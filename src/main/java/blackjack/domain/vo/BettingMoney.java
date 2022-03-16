package blackjack.domain.vo;

public class BettingMoney {
    private static final int MINIMUM_AMOUNT_UNIT = 1000;

    private final int amount;

    public BettingMoney(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    private void validateRange(int value) {
        if (Math.floorMod(value, MINIMUM_AMOUNT_UNIT) != 0) {
            throw new IllegalArgumentException("배팅 금액은 1000단위어야 합니다.");
        }
    }
}
