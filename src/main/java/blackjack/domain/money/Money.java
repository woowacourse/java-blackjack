package blackjack.domain.money;

public class Money {

    private static final String ERROR_NEGATIVE_MONEY = "[ERROR] 투입 금액은 양의 정수여야 합니다.";
    private static final String ERROR_NOT_DIVIDE_BY_THOUSAND = "[ERROR] 투입 금액은 1000원 단위로 넣어야 합니다.";
    private static final int MINIMUM_STANDARD = 1000;

    private final int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        validateNegative(money);
        validateDivideByThousand(money);
    }

    private void validateNegative(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_MONEY);
        }
    }

    private void validateDivideByThousand(int money) {
        if (money % MINIMUM_STANDARD != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDE_BY_THOUSAND);
        }
    }

    public int getMoney() {
        return money;
    }
}
