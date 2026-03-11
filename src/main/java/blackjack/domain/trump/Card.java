package blackjack.domain.trump;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public String getName() {
        return denomination.getSymbol() + suit.getName();
    }

    public int toScore() {
        return denomination.toScore();
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }
}
