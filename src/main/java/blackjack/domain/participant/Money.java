package blackjack.domain.participant;

import blackjack.domain.result.HandResult;
import blackjack.exception.InvalidInputException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Money {

    public static final Money ZERO = new Money(0);
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public static Money getBettingMoney(String bettingMoneyInput) {
        if (!NUMERIC_PATTERN.matcher(bettingMoneyInput).matches()) {
            throw new InvalidInputException("배팅 금액은 0 이상의 정수여야합니다");
        }
        return new Money(Integer.parseInt(bettingMoneyInput));
    }

    public Money getWinningMoney(HandResult result) {
        return new Money((int) (value * result.getProfitRate()));
    }

    public Money add(Money winningMoney) {
        return new Money(value + winningMoney.value);
    }

    public Money toNegative() {
        return new Money(-1 * value);
    }

    public int toInt() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money that = (Money) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
