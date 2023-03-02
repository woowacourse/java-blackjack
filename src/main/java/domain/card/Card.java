package domain.card;

public class Card {

    private final String name;
    private final int value;

    public Card(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
