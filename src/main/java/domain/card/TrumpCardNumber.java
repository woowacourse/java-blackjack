package domain.card;

public enum TrumpCardNumber {
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
    KING("K", 10),
    ACE("A", 11),
    ;

    private final String signature;
    private final int number;

    TrumpCardNumber(String signature, int number) {
        this.signature = signature;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getSignature() {
        return signature;
    }
}
