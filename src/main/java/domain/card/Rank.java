package domain.card;

import domain.result.Score;

import java.util.Arrays;

public enum Rank {
    ACE("Ace", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private static final String NO_EXIST_MESSAGE = "적절한 숫자의 랭크가 존재하지 않습니다.";

    private final String name;
    private final Score score;

    Rank(String name, int score) {
        this.name = name;
        this.score = new Score(score);
    }

    public static Rank of(String name) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.name == name)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_MESSAGE));
    }

    public boolean isAce() {
        return this == Rank.ACE;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
