package blackjack.domain.card;

public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private String name;

    Type(String name) {
        this.name = name;
    }
}
