package domain.card;

public enum CardNumber {
    ACE(1, CardPhrase.ACE),
    TWO(2, CardPhrase.UN_DETERMINE),
    THREE(3, CardPhrase.UN_DETERMINE),
    FOUR(4, CardPhrase.UN_DETERMINE),
    FIVE(5, CardPhrase.UN_DETERMINE),
    SIX(6, CardPhrase.UN_DETERMINE),
    SEVEN(7, CardPhrase.UN_DETERMINE),
    EIGHT(8, CardPhrase.UN_DETERMINE),
    NINE(9, CardPhrase.UN_DETERMINE),
    TEN(10, CardPhrase.UN_DETERMINE),
    JACK(10, CardPhrase.JACK),
    QUEEN(10, CardPhrase.QUEEN),
    KING(10, CardPhrase.KING);

    private final int value;
    private final CardPhrase phrase;

    CardNumber(final int value, final CardPhrase phrase) {
        this.value = value;
        this.phrase = phrase;
    }

    public int value() {
        return value;
    }

    public CardPhrase phrase() {
        return phrase;
    }
}
