package blackjack.domain;

public class Card {

    private final Denomination denomination;
    private final Suit suit;

    public Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return denomination.getName() + suit.getName();
    }
}
