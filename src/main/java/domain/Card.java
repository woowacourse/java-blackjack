package domain;

public class Card {
    private Denomination denomination;
    private Suit suit;
    private int value;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
        this.value = 2;
    }
}
