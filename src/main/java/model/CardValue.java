package model;

public enum CardValue {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final int score;

    CardValue(int score) {
        this.score = score;
    }

    // TODO: 지워야 할 애
    public static CardValue from(int value) {
        for (CardValue cardValue : values()) {
            if (cardValue.score == value) {
                return cardValue;
            }
        }
        throw new IllegalArgumentException("존재하지 않은 카드 번호입니다.");
    }

    public int getScore() {
        return score;
    }
}
