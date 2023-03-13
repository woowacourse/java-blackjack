package blackjack.domain.game;

public class Betting {

    private final int value;

    private Betting(final int value) {
        this.value = value;
    }

    public static Betting from(final int value) {
        return new Betting(value);
    }

    public int getValue() {
        return value;
    }
}
