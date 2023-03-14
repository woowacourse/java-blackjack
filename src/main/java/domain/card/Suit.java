package domain.card;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로바"),
    DIAMOND("다이아몬드");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
