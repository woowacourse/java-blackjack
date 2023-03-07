package domain;

public enum TrumpCardNumber {
    ACE("A", 11),
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
    ;

    private static final int ACE_GAP = 10;

    private final String signature;
    private final int score;

    TrumpCardNumber(String signature, int score) {
        this.signature = signature;
        this.score = score;
    }

    public static int getAceGap() {
        return ACE_GAP;
    }

    public int getScore() {
        return score;
    }

    public String getSignature() {
        return signature;
    }
}
