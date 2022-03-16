package blackjack.domain.money;

public class Money {

    private int money;

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
            throw new IllegalArgumentException("[ERROR] 투입 금액은 양의 정수여야 합니다.");
        }
    }

    private void validateDivideByThousand(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 1000원 단위로 넣어야 합니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
