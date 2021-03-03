package blackjack.domain.card;

public class Card {

    private final String name;
    private final CardSymbol symbol;
    private final CardValue value;

    private Card(final CardSymbol symbol, final CardValue value) {
        this.symbol = symbol;
        this.value = value;
        this.name = value.getName() + symbol.getSymbol();
    }

    public static Card create(final CardSymbol symbol, final CardValue value) {
        return new Card(symbol, value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value.getValue();
    }

    public CardValue getCardValue() {
        return value;
    }
}