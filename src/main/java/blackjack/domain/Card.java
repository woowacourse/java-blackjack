package blackjack.domain;

public class Card {
    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Score getScore() {
        return denomination.getValue();
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }
}
