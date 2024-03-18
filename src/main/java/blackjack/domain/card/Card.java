package blackjack.domain.card;

public class Card {
    private final Denomination denomination;
    private final Suit suit;

    public Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public boolean isAceCard() {
        return denomination == Denomination.ACE;
    }

    public Denomination getNumber() {
        return denomination;
    }

    public Suit getShape() {
        return suit;
    }
}
