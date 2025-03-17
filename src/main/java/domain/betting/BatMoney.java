package domain.betting;

public class BatMoney {
    private final String name;
    private final int money;

    public BatMoney(String name, int money) {
        validateNameIsNotBlank(name);
        validateMoneyIsPositive(money);
        this.name = name;
        this.money = money;
    }

    private void validateNameIsNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("빈 문자열은 이름이 될 수 없습니다.");
        }
    }

    private void validateMoneyIsPositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }
}
