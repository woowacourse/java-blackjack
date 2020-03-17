package domain.card;

public enum Type {
    SPADE("♤"),
    DIAMOND("♢"),
    HEART("♡"),
    CLOVER("♧");

    private String pattern;

    Type(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
