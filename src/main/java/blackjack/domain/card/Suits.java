package blackjack.domain.card;

public enum Suits {
    CLOVER("클로버"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트");

    private final String name;

    Suits(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
