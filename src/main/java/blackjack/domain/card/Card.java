package blackjack.domain.card;

public class Card {

    private final Denomination denomination;
    private final Suit suit;

    public Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public boolean isAceCard() {
        return suit == Suit.ACE;
    }

    public String getDenomination() {
        return denomination.getValue();
    }

    public String getSuitType() {
        return suit.getType();
    }

    public int getSuit() {
        return suit.getValue();
    }
}
