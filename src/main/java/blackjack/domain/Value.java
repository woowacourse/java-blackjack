package blackjack.domain;

public enum Value {

    ACELOW("A", 1),
    ACEHIGH("A", 11), TWO("2", 2), THREE("3", 3),
    FOUR("4", 4), FIVE("5", 5), SIX("6", 6),
    SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9),
    TEN("10", 10), JACK("J", 10), QUEEN("Q", 10), KING("K", 10);

    private final String valueName;
    private final int score;

    Value(String valueName, int score) {
        this.valueName = valueName;
        this.score = score;
    }

    public static Value findValue(int cardNumber) {
        if (cardNumber < 1 || cardNumber >= values().length) {
            throw new IllegalStateException("존재하지 않는 카드 번호입니다.");
        }
        return values()[cardNumber];
    }

    public int getScore() {
        return score;
    }

    public String getValueName() {
        return valueName;
    }
}
