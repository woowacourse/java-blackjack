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

    public int getScore() {
        return denomination.getScore();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public String getNumberName() {
        return denomination.getName();
    }
}
