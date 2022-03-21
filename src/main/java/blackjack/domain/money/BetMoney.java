package blackjack.domain.money;

public class BetMoney {

    private final Money money;

    public BetMoney(final String input) {
        final long value = toLong(input);
        checkPositive(value);

        this.money = Money.valueOf(value);
    }

    private long toLong(final String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("[ERROR] 베팅 금액은 양수여야 합니다.");
        }
    }

    private void checkPositive(final long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 양수여야 합니다.");
        }
    }

    public Money getMoney() {
        return money;
    }
}
