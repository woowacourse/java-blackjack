package util;

public enum Keyword {

    YES("y"),
    NO("n");

    private final String value;

    Keyword(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
