package blackjack.domain;

public class Card {
    private final Suit suit;
    private final Symbol symbol;

    public Card(Suit suit, Symbol symbol) {
        this.suit = suit;
        this.symbol = symbol;
    }

    public int getScore() {
        return symbol.getScore();
    }
}
