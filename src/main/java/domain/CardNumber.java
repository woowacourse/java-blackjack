package domain;

//TODO Number말고 더 좋은 클래스명짓기
public enum CardNumber {

    ACE("A", 1), // TODO: 1 혹은 11
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);


    private final String name;
    private final int value;

    CardNumber(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
