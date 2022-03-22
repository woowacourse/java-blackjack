package blackjack.domain;

public class BettingAmount {

    static final String NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE = "[ERROR] 1000원 단위의 숫자를 입력해야 합니다.";
    private static final int BETTING_AMOUNT_UNIT = 1_000;
    private int value;

    public BettingAmount(int value) {
        isUnitOfThousand(value);
        this.value = value;
    }

    private void isUnitOfThousand(int money) {
        if (money < BETTING_AMOUNT_UNIT || Math.floorMod(money, BETTING_AMOUNT_UNIT) != 0) {
            throw new IllegalArgumentException(NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
