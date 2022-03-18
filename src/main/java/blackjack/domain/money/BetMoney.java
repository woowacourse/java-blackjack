package blackjack.domain.money;

public class BetMoney {

    private final Money money;

    public BetMoney(final String input) {
        this.money = Money.valueOf(createMoneyValue(input));
    }

    private long createMoneyValue(final String input) {
        try {
            final long value = Long.parseLong(input);
            checkPositive(value);
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 양수여야 합니다.");
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
