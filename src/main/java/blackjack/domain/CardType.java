package blackjack.domain;

public enum CardType {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String type;

    CardType(String type) {
        this.type = type;
    }
}
