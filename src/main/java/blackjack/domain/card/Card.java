package blackjack.domain.card;

import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;

import java.util.Objects;

import static blackjack.domain.card.painting.Symbol.ACE;

public class Card {
    private final Suit suit;
    private final Symbol symbol;

    public Card(Suit suit, Symbol symbol) {
        this.suit = suit;
        this.symbol = symbol;
    }

    public boolean isAce() {
        return symbol == ACE;
    }

    public boolean isSameSuit(Suit suit) {
        return this.suit == suit;
    }

    public boolean isSameSymbol(Symbol symbol) {
        return this.symbol == symbol;
    }

    public String getSuitLetter() {
        return suit.getLetter();
    }

    public String getSymbolLetter() {
        return symbol.getLetter();
    }

    public int getScore() {
        return symbol.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, symbol);
    }
}
