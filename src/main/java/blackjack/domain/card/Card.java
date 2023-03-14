package blackjack.domain.card;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination.equals(Denomination.ACE);
    }

    public Suit getSymbol() {
        return suit;
    }

    public Denomination getNumber() {
        return denomination;
    }
}
