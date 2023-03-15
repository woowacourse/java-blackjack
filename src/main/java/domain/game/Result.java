package domain.game;

import java.util.function.Supplier;

public enum Result {
    BLACKJACK_WIN("승", () -> 1.5),
    WIN("승", () -> 1.0),
    PUSH("무", () -> 0.0),
    LOSE("패", () -> -1.0);

    private final String name;
    private final Supplier<Double> exchangeRate;

    Result(final String name, Supplier<Double> exchangeRate) {
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public String getName() {
        return this.name;
    }

    public double getExchangeRate() {
        return exchangeRate.get();
    }
}
