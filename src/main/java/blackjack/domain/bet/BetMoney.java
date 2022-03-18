package blackjack.domain.bet;

import java.util.Objects;

public class BetMoney {

    private static final int MIN_AMOUNT = 1;
    private static final String UNDER_MIN_AMOUNT_ERROR_MESSAGE = "배팅 금액은 " + MIN_AMOUNT + "원 이상입력해야합니다.";

    private final int money;

    public BetMoney(int money) {
        validateMinAmount(money);

        this.money = money;
    }

    private void validateMinAmount(int money) {
        if (money < MIN_AMOUNT) {
            throw new IllegalArgumentException(UNDER_MIN_AMOUNT_ERROR_MESSAGE);
        }
    }

    public int get() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BetMoney betMoney = (BetMoney) o;
        return money == betMoney.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return "BetMoney{" +
                "money=" + money +
                '}';
    }
}
