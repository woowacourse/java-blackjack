package domain;

public class Card {
    private final CardType type;
    private final CardNumber number;

    public Card(final CardType type, final CardNumber number) {
        this.type = type;
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    public CardNumber getNumber() {
        return number;
    }
}
