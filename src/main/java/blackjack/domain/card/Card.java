package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Symbol symbol;

    public Card(Suit suit, Symbol symbol) {
        Objects.requireNonNull(suit, "suit은 필수입니다");
        Objects.requireNonNull(symbol, "symbol은 필수입니다");
        this.suit = suit;
        this.symbol = symbol;
    }

    public int getScore() {
        return symbol.getScore();
    }

    public boolean isAce() {
        return this.symbol.isAce();
    }

    @Override
    public String toString() {
        return suit.getName() + symbol.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, symbol);
    }
}
