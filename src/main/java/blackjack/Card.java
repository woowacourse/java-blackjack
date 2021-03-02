package blackjack;

public class Card {

    private final String name;
    private final String value;

    private Card(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public static Card create(final String symbol, final String value) {
        return new Card(symbol, value);
    }
}