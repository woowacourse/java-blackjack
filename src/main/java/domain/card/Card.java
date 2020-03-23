package domain.card;

import java.util.Objects;

public class Card {
    static final String INVALID_ACE_ONLY_NOTACE_ARE_ALLOWED_MEESAGE = "A는 당 함수를 활용할 수 없습니다.";
    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    int calculateExceptAce() {
        if (symbol.equals(Symbol.ACE)) {
            throw new IllegalStateException(INVALID_ACE_ONLY_NOTACE_ARE_ALLOWED_MEESAGE);
        }

        return symbol.getValue();
    }

    int calculate(int sum) {
        return symbol.calculate(sum);
    }

    boolean isAce() {
        return symbol.equals(Symbol.ACE);
    }

    boolean isNotAce() {
        return !symbol.equals(Symbol.ACE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol &&
                type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    @Override
    public String toString() {
        return symbol.getValue() + type.getPattern();
    }
}
