package domain.betting;

import java.util.Objects;

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

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BatMoney batMoney = (BatMoney) o;
        return money == batMoney.money && Objects.equals(name, batMoney.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }

    @Override
    public String toString() {
        return "BatMoney{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
