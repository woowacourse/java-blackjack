package domain.card;

public class Card {
    private final Denomination denomination;
    private final Suits suit;

    public Card(final Denomination denomination, final Suits suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public String getCardName() {
        return denomination.getPoint() + suit.getName();
    }

    public Score getScore() {
        return denomination.getScore();
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suits getSuit() {
        return suit;
    }
}
