package blackjack.domain;

enum CardShape {

    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String shape;

    CardShape(String shape) {
        this.shape = shape;
    }
}
