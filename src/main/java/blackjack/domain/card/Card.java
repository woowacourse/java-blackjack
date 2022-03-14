package blackjack.domain.card;

public class Card {

    private final Denomination denomination;
    private final Suit suit;

    public Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public String getDenominationName() {
        return denomination.getName();
    }

    public String getSuitName() {
        return suit.getName();
    }

    @Override
    public String toString() {
        return denomination.getName() + suit.getName();
    }
}
