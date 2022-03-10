package blackjack;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
