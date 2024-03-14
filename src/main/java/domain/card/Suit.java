package domain.card;

public enum Suit {
    CLOVER("클로버"),
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
