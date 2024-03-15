package model.money;

import java.util.Objects;

public class Money {
    private final int amount;

    Money(int money) {
        this.amount = money;
    }

    public static Money createBettingAmount(String moneyText) {
        int money = parseIntWithCustomException(moneyText);
        validate(money);
        return new Money(money);
    }

    private static int parseIntWithCustomException(String moneyText) {
        try{
            return Integer.parseInt(moneyText);
        }catch (Exception e) {
            throw new IllegalArgumentException("배팅 금액은 정수만 입력해주세요.");
        }
    }

    private static void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야합니다.");
        }
    }

    public Money createMultiplyOf(int operand) {
        return new Money(amount * operand);
    }

    public Money createMultiplyOf(double operand) {
        return new Money((int) (amount * operand));
    }

    public Money increase(Money money) {
        validateNegative(money);
        return new Money(this.amount + money.amount);
    }

    public Money decrease(Money money) {
        validateNegative(money);
        return new Money(this.amount - money.amount);
    }

    private void  validateNegative(Money money) {
        if (money.amount < 0) {
            throw new IllegalArgumentException("더할 값은 양수여야만 한다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
