package domain.card;

public enum Rank {
    ACE("A", 11),
    NUM2("2", 2),
    NUM3("3", 3),
    NUM4("4", 4),
    NUM5("5", 5),
    NUM6("6", 6),
    NUM7("7", 7),
    NUM8("8", 8),
    NUM9("9", 9),
    NUM10("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String description;
    private final int score;

    Rank(String description, int score) {
        this.description = description;
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public int getScore() {
        return score;
    }
}
