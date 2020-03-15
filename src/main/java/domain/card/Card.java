package domain.card;

import java.util.Objects;

public class Card {
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
            throw new IllegalArgumentException("잘못된 값이 들어왔습니다.");
        }
    }

    public boolean isSameWith(Type type, Symbol symbol) {
        return this.type == type && this.symbol == symbol;
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
