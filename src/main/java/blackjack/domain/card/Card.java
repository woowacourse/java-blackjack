package blackjack.domain.card;

public class Card {

    private final String name;
    private final Denomination value;

    private Card(final Suit symbol, final Denomination value) {
        this.value = value;
        this.name = value.getName() + symbol.getSymbol();
    }

    public static Card create(final Suit symbol, final Denomination value) {
        return new Card(symbol, value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value.getValue();
    }

    public Denomination getCardValue() {
        return value;
    }
}