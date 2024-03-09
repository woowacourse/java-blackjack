package blackjack.domain.card;

public enum Shape {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String value;

    Shape(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
