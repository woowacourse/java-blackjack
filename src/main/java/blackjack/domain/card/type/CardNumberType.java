package blackjack.domain.card.type;

public enum CardNumberType {
    ACE("A", 1),
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

    public static final int BLACKJACK = 21;
    private static final int ACE_HIGH_VALUE = 11;

    private final String number;
    private final int value;

    CardNumberType(String number, int value) {
        this.number = number;
        this.value = value;
    }

    public String getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }

    public int getValue(CardNumberType cardNumber, int scoreSum) {
        if (cardNumber == ACE) {
            return aceCase(cardNumber, scoreSum);
        }
        return cardNumber.getValue();
    }

    private int aceCase(CardNumberType cardNumber, int scoreSum) {
        if (scoreSum + ACE_HIGH_VALUE <= BLACKJACK) {
            return ACE_HIGH_VALUE;
        }
        return cardNumber.getValue();
    }
}
