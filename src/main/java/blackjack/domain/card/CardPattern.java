package blackjack.domain.card;

public enum CardPattern {

    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String pattern;

    CardPattern(final String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
