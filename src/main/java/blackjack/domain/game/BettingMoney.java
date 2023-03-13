package blackjack.domain.game;

public class BettingMoney {

    private static final int BETTING_MIN_VALUE = 1_000;
    private static final int BETTING_MAX_VALUE = 100_000_000;
    private static final int MONEY_UNIT = 100;

    private final int money;

    public BettingMoney(int money) {
        validateRange(money);
        validateUnit(money);
        this.money = money;
    }

    private void validateRange(int value) {
        if (value < BETTING_MIN_VALUE || value > BETTING_MAX_VALUE) {
            throw new IllegalArgumentException("배팅 금액은 최소 1천원, 최대 1억원까지 가능합니다.");
        }
    }

    private void validateUnit(int money) {
        if (money % MONEY_UNIT > 0) {
            throw new IllegalArgumentException("배팅 금액은 백원 단위로 입력 가능합니다.");
        }
    }

    public int getValue() {
        return money;
    }
}
