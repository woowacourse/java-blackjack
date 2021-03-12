package blackjack.domain.player;

import java.util.Objects;

public class Money {

    public static final int INVERS = -1;
    private int money;

    public Money(String money) {
        this(ChangeToInt(money));
    }

    public Money(int money) {
        this.money = money;
    }

    private static int ChangeToInt(String input) {
        validateEmpty(input);
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateEmpty(String money) {
        if (money.isEmpty()) {
            throw new IllegalArgumentException("공백을 입력으로 받을 수 없습니다.");
        }
    }

    private static void validateNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }
    }

    public Money calculateProfit(double yield) {
        return new Money((int) (money * yield));
    }

    public Money inverseMoney() {
        return new Money(INVERS * money);
    }

    public Money addMoney(Money money) {
        return new Money(this.money + money.money);
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
