package domain.card;

import java.util.Objects;

/**
 * 카드 한장을 의미하는 객체
 */
public class Card {
    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public boolean isAce() {
        return this.symbol.equals(Symbol.ACE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    @Override
    public String toString() {
        return "Card{" +
            "symbol=" + symbol +
            ", type=" + type +
            '}';
    }

    public String getSymbolName() {
        return this.symbol.getSymbolName();
    }

    public int getSymbolScore() {
        return this.symbol.getScore();
    }

    public String getTypeKorean() {
        return this.type.getKoreanName();
    }
}
