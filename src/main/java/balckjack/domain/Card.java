package balckjack.domain;

abstract public class Card {

    private final String name;
    private final int value;

    public Card(String name, int value) {
        validateValue(value);
        this.name = name;
        this.value = value;
    }
    abstract void validateValue(int value);
}
