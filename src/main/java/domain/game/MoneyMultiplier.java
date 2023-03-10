package domain.game;

public class MoneyMultiplier {
    private final double multiplier;

    private MoneyMultiplier(final double multiplier) {
        this.multiplier = multiplier;
    }

    public static MoneyMultiplier create(final double multiplier) {
        return new MoneyMultiplier(multiplier);
    }

    public double getMultiplier() {
        return multiplier;
    }
}
