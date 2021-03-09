package blackjack.domain.card;

public class Card {
    private final Number number;
    private final Suit suit;

    public Card(Number number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public boolean isAce() {
        return Number.ACE.equals(number);
    }

    public int getValue() {
        return number.getValue();
    }

    public String getNumber() {
        return number.getNumber();
    }

    public String getShape() {
        return suit.getName();
    }
}
