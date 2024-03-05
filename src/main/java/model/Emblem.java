package model;

public enum Emblem {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버"),
    HEART("하트");

    private final String value;

    Emblem(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
