package blackjack.domain.card;

public final class Card {

    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";

    private final Type type;
    private final Symbol symbol;

    public Card(Type type, Symbol symbol) {
        validateNotNull(type, symbol);
        this.type = type;
        this.symbol = symbol;
    }

    private void validateNotNull(Type type, Symbol symbol) {
        if (type == null || symbol == null) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
    }

    public boolean isAce() {
        return this.symbol == Symbol.ACE;
    }

    public int getValue() {
        return symbol.getValue();
    }

    @Override
    public String toString() {
        return symbol.getName() + type.getName();
    }
}
