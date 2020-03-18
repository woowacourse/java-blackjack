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

    @Override
    public String toString() {
        return suit.getName() + symbol.getName();
    }

    public boolean isAce() {
        return this.symbol.isAce();
    }
}
