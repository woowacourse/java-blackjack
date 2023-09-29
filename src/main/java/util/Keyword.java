package util;

public enum Keyword {

    DEALER("딜러"),
    NAME_SPLITTER(","),
    BLANK(" "),
    YES("y"),
    NO("n"),
    WIN("승"),
    SAME("무"),
    LOSE("패");

    private final String value;

    Keyword(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int length() {
        return value.length();
    }
}
