package Blackjack.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record Bet(int amount) {
    private static final Map<Integer, Bet> CACHE = new ConcurrentHashMap<>();

    public static Bet startingBet() {
        return valueOf(0);
    }

    public static Bet valueOf(final int value) {
        return CACHE.computeIfAbsent(value, Bet::new);
    }

    public Bet increase(final int value) {
        return valueOf(this.amount + value);
    }
}
