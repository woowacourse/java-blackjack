package domain.game;

import java.util.Collection;

public class GamblingMoney {

    private static final GamblingMoney ZERO = new GamblingMoney(0);

    private final int amount;

    public GamblingMoney(int amount) {
        this.amount = amount;
    }

    public GamblingMoney onceHalf() {
        return new GamblingMoney((int) (amount * 1.5));
    }

    public GamblingMoney negative() {
        return new GamblingMoney(-amount);
    }

    public GamblingMoney calculateProfit(Winning winning) {
        GamblingMoney finalized = finalize(winning);
        return new GamblingMoney(finalized.amount - amount);
    }

    private GamblingMoney finalize(Winning winning) {
        if (winning == Winning.WIN) {
            return new GamblingMoney(amount * 2);
        }
        if (winning == Winning.LOSE) {
            return ZERO;
        }
        return this;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof GamblingMoney money)) {
            return false;
        }

        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return amount;
    }

    @Override
    public String toString() {
        return "GamblingMoney{" +
            "amount=" + amount +
            '}';
    }

    public static GamblingMoney sum(Collection<GamblingMoney> moneys) {
        int sum = moneys.stream()
            .mapToInt(GamblingMoney::getAmount)
            .sum();
        return new GamblingMoney(sum);
    }

    public static GamblingMoney bet(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("배팅금은 최소 1000원 이상입니다.");
        }
        if (amount > 1_000_000) {
            throw new IllegalArgumentException("배팅금은 100만원까지만 가능합니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("배팅금은 1000원 단위로만 가능합니다.");
        }
        return new GamblingMoney(amount);
    }
}
