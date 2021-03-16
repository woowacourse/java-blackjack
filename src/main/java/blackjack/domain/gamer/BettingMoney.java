package blackjack.domain.gamer;

import blackjack.domain.utils.RegexUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class BettingMoney {
    public static final BettingMoney ZERO = new BettingMoney(0);
    private static final String EXCEPTION_NOT_LONG_TYPE = "베팅금액은 소수점이 없는 숫자여야 합니다.";

    private final BigDecimal money;

    public BettingMoney(String value) {
        this(validateIsLong(value));
    }

    public BettingMoney(long value) {
        this(BigDecimal.valueOf(value));
    }

    public BettingMoney(BigDecimal value) {
        this.money = value;
    }

    private static long validateIsLong(String value) {
        if (RegexUtil.isLong(value)) {
            return Long.parseLong(value);
        }
        throw new IllegalArgumentException(EXCEPTION_NOT_LONG_TYPE);
    }

    public BettingMoney subtract(BettingMoney value) {
        return new BettingMoney(money.subtract(value.money));
    }

    public BettingMoney multiply(double value) {
        final BigDecimal result = money
            .multiply(new BigDecimal(value))
            .setScale(0, RoundingMode.FLOOR);
        return new BettingMoney(result);
    }

    public BigDecimal toBigDecimal() {
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
        BettingMoney bettingMoney1 = (BettingMoney) o;
        return Objects.equals(money, bettingMoney1.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

}
