package card;

public enum Pattern {

    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    SPADE("스페이드");

    private final String pattern;

    Pattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
