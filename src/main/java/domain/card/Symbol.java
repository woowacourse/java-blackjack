package domain.card;

public enum Symbol {
    HEART("하트"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드");

    private final String value;

    Symbol(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
