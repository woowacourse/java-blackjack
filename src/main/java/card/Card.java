package card;

import java.util.Objects;

public class Card {
    private static final String ERROR_NULL_MESSAGE = "잘못된 값이 들어왔습니다.";

    private final Type type;
    private final Symbol symbol;

    /* Package private */
    Card(Type type, Symbol symbol) {
        validateNull(type, symbol);
        this.type = type;
        this.symbol = symbol;
    }

    private void validateNull(Type type, Symbol symbol) {
        if (Objects.isNull(type) || Objects.isNull(symbol)) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(type, card.type) &&
                Objects.equals(symbol, card.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, symbol);
    }

    public boolean isAce() {
        return symbol == Symbol.ACE;
    }

    public int getValue() {
        return symbol.getScore();
    }

    public String getName() {
        return symbol.getName() + type.getType();
    }
}
