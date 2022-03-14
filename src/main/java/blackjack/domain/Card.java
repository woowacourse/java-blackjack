package blackjack.domain;

public class Card {

    private final Suit suit;
    private final Symbol symbol;

    public Card(Suit suit, Symbol symbol) {
        this.suit = suit;
        this.symbol = symbol;
    }

    public boolean isAce() {
        return symbol.equals(Symbol.ACE);
    }

    public int sumPoint(int points) {
        return points + symbol.getPoint();
    }

    public Suit getSuit() {
        return suit;
    }

    public Symbol getRank() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        return symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        int result = suit != null ? suit.hashCode() : 0;
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        return result;
    }
}
