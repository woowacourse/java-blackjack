package blackjack.domain;

public class Card {
    private final Suit suit;
    private final Letter letter;

    public Card(Suit suit, Letter letter) {
        this.suit = suit;
        this.letter = letter;
    }

    public Suit getTrumpShape() {
        return suit;
    }

    public Letter getTrumpNumber() {
        return letter;
    }
}
