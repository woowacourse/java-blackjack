package domain.card;

public enum Suit {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
