package domains.user.money;

import java.util.Objects;

public abstract class Money {
    protected int money;

    protected void checkValidation(String input) {
        checkNullAndEmpty(input);
        checkNumberFormat(input);
    }

    private void checkNullAndEmpty(String money) {
        if (Objects.isNull(money) || money.isEmpty()) {
            throw new InvalidMoneyException(InvalidMoneyException.NULL_OR_EMPTY);
        }
    }

    private void checkNumberFormat(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException(InvalidMoneyException.NOT_NUMBER_FORMAT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return String.valueOf(money);
    }
}
