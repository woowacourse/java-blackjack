package domain.card;

public enum CardSuit {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    CardSuit(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
