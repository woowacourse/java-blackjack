package blackjack.domain.card;

public enum CardLetter {
    ACE("A", 11, 1),
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

    private final String letter;
    private final int value;
    private final int extraValue;

    CardLetter(final String letter, final int value, final int extraValue) {
        this.letter = letter;
        this.value = value;
        this.extraValue = extraValue;
    }

    CardLetter(final String letter, final int value) {
        this(letter, value, value);
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public String getLetter() {
        return this.letter;
    }

    public int getValue() {
        return this.value;
    }

    public int getExtraValue() {
        return this.extraValue;
    }
}
