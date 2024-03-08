package domain.card;

public enum Emblem {
    CLOVER("클로버"),
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String name;

    Emblem(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
