package blackjack;

public class Card {
    private String name;
    private int value;

    public Card(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return value == 1;
    }
}
