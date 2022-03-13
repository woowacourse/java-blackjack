package blackjack.domain;

public class Card {

    private final Suit suit;
    private final Symbols symbols;

    public Card(Suit suit, Symbols symbols) {
        this.suit = suit;
        this.symbols = symbols;
    }

    public boolean isAce() {
        return symbols.equals(Symbols.ACE);
    }

    public int sumPoint(int points) {
        return points + symbols.getPoint();
    }

    public Suit getSuit() {
        return suit;
    }

    public Symbols getRank() {
        return symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        return symbols == card.symbols;
    }

    @Override
    public int hashCode() {
        int result = suit != null ? suit.hashCode() : 0;
        result = 31 * result + (symbols != null ? symbols.hashCode() : 0);
        return result;
    }
}
