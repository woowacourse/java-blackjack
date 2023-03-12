package domain.card;

import domain.hand.Score;

public enum Letter {

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
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    private final String letter;
    private final int score;

    Letter(String letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public boolean isA() {
        return this == ACE;
    }

    public String getLetter() {
        return letter;
    }

    public Score getScore() {
        return new Score(score);
    }
}
