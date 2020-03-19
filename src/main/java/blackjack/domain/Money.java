package blackjack.domain;

public class Money {

    private static final String NOT_POSITIVE_EXCEPTION_MESSAGE = "금액은 양수여야합니다.";
    private static final String MINIMUM_UNIT_EXCEPTION_MESSAGE = "금액의 최소 단위는 10입니다.";

    private final int money;

    private Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    public static Money of(String money) {
        try {
            return new Money(Integer.parseInt(money));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void validateMoney(int money) {
        if (money < 1) {
            throw new IllegalArgumentException(NOT_POSITIVE_EXCEPTION_MESSAGE);
        }
        if (money % 10 != 0) {
            throw new IllegalArgumentException(MINIMUM_UNIT_EXCEPTION_MESSAGE);
        }
    }
}
