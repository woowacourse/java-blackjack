package blackjack.domain;

import java.util.Objects;

public class Card implements Comparable<Card>{
    private static final int ACE_UPPER_POINT = 11;

    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public int getPoint(int score) {
        if (isAvailableAceUpperPoint(score)) {
            return ACE_UPPER_POINT;
        }
        return type.getPoint();
    }

    private boolean isAvailableAceUpperPoint(int score) {
        return isAce() && score <= ACE_UPPER_POINT;
    }

    public boolean isAce() {
        return type == Type.ACE;
    }

    public Type getType() {
        return type;
    }

    public Symbol getSymbol() {
        return symbol;
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
        return symbol == card.symbol &&
                type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    @Override
    public int compareTo(Card targetCard) {
        if (this.type.getPoint() < targetCard.type.getPoint()) {
            return 1;
        }
        return -1;
    }
}
