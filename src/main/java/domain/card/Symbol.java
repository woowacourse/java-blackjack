package domain.card;

public enum Symbol {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private static final int ELEVEN_ACE_VALUE = 11;
    private static final int ONE_ACE_VALUE = 1;
    private int value;
    private String word;

    Symbol(int value, String word) {
        this.value = value;
        this.word = word;
    }

    public void selectAce(int sumWithOutAceValue) {
        if (sumWithOutAceValue + ELEVEN_ACE_VALUE <= 21) {
            this.value = ELEVEN_ACE_VALUE;
            return;
        }
        this.value = ONE_ACE_VALUE;
    }

    public String getWord() {
        return word;
    }

    public int getValue() {
        return value;
    }
}
