package domain.card;

public final class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAceCard() {
        return denomination.isAce();
    }

    public int getScore() {
        return denomination.getScore();
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
