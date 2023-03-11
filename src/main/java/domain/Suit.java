package domain;

public enum Suit {

    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
