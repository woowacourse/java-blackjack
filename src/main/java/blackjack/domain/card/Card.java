package blackjack.domain.card;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public int getPoint() {
        return denomination.getPoint();
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
