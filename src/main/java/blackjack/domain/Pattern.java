package blackjack.domain;

public enum Pattern {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    Pattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
