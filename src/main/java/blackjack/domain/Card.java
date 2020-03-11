package blackjack.domain;

public class Card {
    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public String toString() {
        return type.getSimpleName() + symbol.getKoreanName();
    }
}
