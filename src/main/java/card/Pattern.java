package card;

public enum Pattern {
    HEART("하트"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드");

    private final String value;

    Pattern(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
