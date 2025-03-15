package blackjack.domain.card;

public enum Denomination {
    ACE("A", 1, 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String text;
    private final int minValue;
    private final int maxValue;

    Denomination(String text, int minValue) {
        this(text, minValue, minValue);
    }

    Denomination(String text, int minValue, int maxValue) {
        this.text = text;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static int convertAceValue(final int sum, final int threshold) {
        int convertedSum = sum + ACE.maxValue - ACE.minValue;
        if (convertedSum <= threshold) {
            return convertedSum;
        }
        return sum;
    }

    public String getText() {
        return text;
    }

    public int getMinValue() {
        return minValue;
    }
}
