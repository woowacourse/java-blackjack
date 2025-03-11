package blackjack.domain;

public class BettingMoney {
    private final int value;

    public BettingMoney(int value) {
        validMoney(value);
        this.value = value;
    }

    private void validMoney(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("[Error] 배팅 금액은 음수가 될 수 없습니다.");
        }
    }

    public int intValue() {
        return value;
    }

}
