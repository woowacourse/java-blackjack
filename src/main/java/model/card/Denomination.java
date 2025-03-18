package model.card;

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

    public static final int ORIGINAL_ACE_VALUE = 11;
    private static final int FACE_CARD_VALUE = 10;
    private final String value;

    Denomination(String value) {
        this.value = value;
    }

    public static int parseDenominationValue(String input) {
        if (Character.isDigit(input.charAt(0))) {
            return Integer.parseInt(input);
        }
        if (input.equals(ACE.getValue())) {
            return ORIGINAL_ACE_VALUE;
        }
        return FACE_CARD_VALUE;
    }

    public String getValue() {
        return value;
    }
}
