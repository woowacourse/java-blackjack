package blackjack.domain.card;

public class Card {
    public static final String BLANK = " ";
    private final Suit suit;
    private final Symbol symbol;

    public Card(Suit suit, Symbol symbol) {
        this.suit = suit;
        this.symbol = symbol;
    }

    public int getScore() {
        return symbol.getScore();
    }

    @Override
    public String toString() {
        return suit.getName() +
                BLANK +
                symbol.getName();
    }

    public boolean isAce() {
        return this.symbol.name().equals("ACE");
    }
}
