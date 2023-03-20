package domain.card;

import domain.hand.Score;

public enum Letter {

    ACE("A", new Score(1)),
    TWO("2", new Score(2)),
    THREE("3", new Score(3)),
    FOUR("4", new Score(4)),
    FIVE("5", new Score(5)),
    SIX("6", new Score(6)),
    SEVEN("7", new Score(7)),
    EIGHT("8", new Score(8)),
    NINE("9", new Score(9)),
    TEN("10", new Score(10)),
    KING("K", new Score(10)),
    QUEEN("Q", new Score(10)),
    JACK("J", new Score(10));

    private final String letter;
    private final Score score;

    Letter(String letter, Score score) {
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
        return score;
    }
}
