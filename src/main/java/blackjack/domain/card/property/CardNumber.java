package blackjack.domain.card.property;

public enum CardNumber {
    A("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    Q("Q", 10),
    K("K", 10),
    J("J", 10)
    ;

    private final String name;
    private final int number;

    CardNumber(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public boolean isA() {
        return this.equals(A);
    }
}
