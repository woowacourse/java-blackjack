package domain;

public class Card {
    private final int number;
    private final CardType type;

    public Card(final int number, final CardType type) {
        this.number = number;
        this.type = type;
    }
}
