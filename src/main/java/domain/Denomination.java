package domain;

public enum Denomination {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String value;

    Denomination(String value) {
        this.value = value;
    }

    public static int parseInt(Denomination input) {
        for (Denomination denomination : values()) {
            Integer value1 = matchIntValue(input, denomination);
            if (value1 != null) {
                return value1;
            }
        }
        throw new IllegalArgumentException("Invalid denomination: " + input);
    }

    private static Integer matchIntValue(Denomination input, Denomination denomination) {
        if (denomination.equals(input)) {
            if (Character.isDigit(input.value.charAt(0))) {
                return Integer.parseInt(input.value);
            }
            if (input.equals(ACE)) {
                return 11;
            }
            return 10;
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
