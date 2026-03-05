package domain;

public enum CardContents {
    A("A", Integer.MAX_VALUE),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    J("J", 10),
    Q("Q", 10),
    K("K", 10);

    private final String number; // 카드 번호
    private final int score;

    CardContents(String number, int score) {
        this.number = number;
        this.score = score;
    }

    public String getNumber() {
        return number;
    }

    public int getScore() {
        return score;
    }
}
