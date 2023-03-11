package blackjack.domain.card;

public class Card {

    private final Number number;
    private final Suit suit;

    public Card(final Number number, final Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public boolean isAce() {
        return number == Number.ACE;
    }

    public int getScore() {
        return number.getScore();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public String getNumberName() {
        return number.getName();
    }
}
