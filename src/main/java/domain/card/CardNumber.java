package domain.card;

public enum CardNumber {
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

    private final String number;

    CardNumber(String number) {
        this.number = number;
    }

    public static int fromScore(CardNumber cardNumber) {
        if (cardNumber == CardNumber.ACE) {
            return 1;
        }
        if (cardNumber == CardNumber.JACK || cardNumber == CardNumber.QUEEN || cardNumber == CardNumber.KING) {
            return 10;
        }
        return Integer.parseInt(cardNumber.getNumber());
    }

    public String getNumber() {
        return number;
    }
}
