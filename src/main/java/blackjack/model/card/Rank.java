package blackjack.model.card;

public enum Rank {

    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    J("J", 10),
    Q("Q", 10),
    K("K", 10),
    ;

    private final String format;
    private final int score;

    Rank(String format, int score) {
        this.format = format;
        this.score = score;
    }

    public String getFormat() {
        return format;
    }

    public int getScore() {
        return score;
    }
}
