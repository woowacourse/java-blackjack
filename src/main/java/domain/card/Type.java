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

    public String getPattern() {
        return pattern;
    }
}
