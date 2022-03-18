package blackjack.domain.game;

import java.util.Objects;
import java.util.regex.Pattern;

public class BettingMoney {

    private static final BettingMoney zeroBettingMoney = new BettingMoney(0);

    private static final Pattern NATURAL_NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]*$");
    private static final int BATTING_MONEY_UNIT = 10;

    private final int value;

    private BettingMoney(final int value) {
        this.value = value;
    }

    public BettingMoney(final String value) {
        Objects.requireNonNull(value, "배팅 금액에는 null이 들어올 수 없습니다.");
        validateBlank(value);
        validateNaturalNumber(value);
        final int battingMoney = Integer.parseInt(value);
        validateUnit(battingMoney);
        this.value = battingMoney;
    }

    private void validateNaturalNumber(final String value) {
        if (!NATURAL_NUMBER_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("배팅 금액은 자연수여야 합니다.");
        }
    }

    private void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("배팅 금액은 빈 값으로 만들 수 없습니다.");
        }
    }

    private void validateUnit(final int value) {
        if (value % BATTING_MONEY_UNIT != 0) {
            throw new IllegalArgumentException("배팅 금액의 단위는 10입니다.");
        }
    }

    public int getProfit(final double profitRate) {
        return (int) (value * profitRate);
    }

    public static BettingMoney getDealerBattingMoney() {
        return zeroBettingMoney;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BettingMoney that = (BettingMoney) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
