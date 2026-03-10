package blackjack.domain;

public enum Figure {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    Figure(String name) {
        this.name = name;
    }

    public static int getSize() {
        return Figure.values().length;
    }

    public String getName() {
        return name;
    }
}
