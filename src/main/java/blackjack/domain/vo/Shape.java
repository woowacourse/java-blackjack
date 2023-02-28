package blackjack.domain.vo;

public enum Shape {
    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버")
    ;

    private final String value;

    Shape(String value) {
        this.value = value;
    }
}
