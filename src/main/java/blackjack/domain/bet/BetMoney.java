package blackjack.domain.bet;

import java.util.Objects;

public class BetMoney {

    private static final int AMOUNT_STANDARD = 10;
    private static final String AMOUNT_STANDARD_ERROR_MESSAGE = "배팅 금액은 " + AMOUNT_STANDARD + "원 단위로 입력해야합니다.";
    private static final String NEGATIVE_OR_ZERO_ERROR_MESSAGE = "배팅 금액은 양수로 입력해야합니다.";

    private final int money;

    public BetMoney(int money) {
        validateAmount(money);
        validateNegativeOrZero(money);

        this.money = money;
    }

    private void validateAmount(int money) {
        if (money % AMOUNT_STANDARD != 0) {
            throw new IllegalArgumentException(AMOUNT_STANDARD_ERROR_MESSAGE);
        }
    }

    private void validateNegativeOrZero(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(NEGATIVE_OR_ZERO_ERROR_MESSAGE);
        }
    }

    public int get() {
        return money;
    }

    public Profit getProfit(double rate) {
        return new Profit((int) (money * rate));
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
