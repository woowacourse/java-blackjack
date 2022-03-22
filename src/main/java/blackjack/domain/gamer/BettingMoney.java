package blackjack.domain.gamer;

public class BettingMoney {
    private static final int BETTING_MONEY_UNIT = 100;
    int value;

    public BettingMoney(int value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(int value) {
        if (value % BETTING_MONEY_UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 100단위만 가능합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
