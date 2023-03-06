package domain.card;

public class Card {

    private final CardType type;
    private final CardNumber number;

    public Card(final CardType type, final CardNumber number) {
        this.type = type;
        this.number = number;
    }

    public boolean hasNumberOf(final CardNumber otherNumber) {
        return number.equals(otherNumber);
    }


    public CardType getType() {
        return type;
    }

    public CardNumber getNumber() {
        return number;
    }
}
