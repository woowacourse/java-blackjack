package domain.game;

import java.util.Objects;

public class Revenue {

    private final double amount;

    public Revenue(final double amount) {
        this.amount = amount;
    }

    public static Revenue lose(final BattingMoney money) {
        return new Revenue(money.amount() * -1);
    }

    public static Revenue defaultWin(final BattingMoney money) {
        return new Revenue(money.amount());
    }

    public static Revenue draw(final BattingMoney money) {
        return new Revenue(0);
    }

    public static Revenue blackJackWin(final BattingMoney money) {
        return new Revenue(money.amount() * 1.5);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Revenue)) return false;
        final Revenue revenue = (Revenue) o;
        return Double.compare(revenue.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public double amount() {
        return amount;
    }
}
