package domain.card;

public enum Type {
    SPADE("♤"),
    DIAMOND("♢"),
    HEART("♡"),
    CLOVER("♧");

    private final String pattern;

    Type(String pattern) {
        this.pattern = pattern;
    }

    String getPattern() {
        return pattern;
    }
}
