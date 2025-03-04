package domain;

public enum TrumpShape {

    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String value;

    TrumpShape(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
