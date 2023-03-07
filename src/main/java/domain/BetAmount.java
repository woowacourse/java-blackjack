package domain;

import java.util.Objects;

public class BetAmount {
    private static final int MINIMUM = 100;
    private static final int MAXIMUM = 100_000_000;
    private static final int RATE_OF_WINNING = 2;
    private static final double RATE_OF_BLACKJACK = 2.5;

    private final int money;

    private BetAmount(int money) {
        this.money = money;
    }

    public static BetAmount from(int money) {
        validate(money);

        return new BetAmount(money);
    }

    private static void validate(int money) {
        if (money < MINIMUM || money > MAXIMUM) {
            throw new IllegalArgumentException("배팅 금액은 100원 이상, 1억 이하여야 합니다.");
        }
    }

    public BetAmount receiveDouble() {
        return new BetAmount(money * RATE_OF_WINNING);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetAmount betAmount = (BetAmount) o;
        return money == betAmount.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    public int getMoney() {
        return money;
    }

    public BetAmount receiveWithBlackjack() {
        return new BetAmount((int) (money * RATE_OF_BLACKJACK));
    }
}
