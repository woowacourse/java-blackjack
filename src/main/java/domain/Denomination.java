package domain;

import java.util.Arrays;

public enum Denomination {

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

    Denomination(String letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public static Denomination of(String letter) {
        return Arrays.stream(values())
                .filter(denomination1 -> denomination1.letter().equals(letter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 카드 글자입니다"));
    }

    public String letter() {
        return letter;
    }

    public Score score() {
        return new Score(this.score);
    }

    public boolean isAce() {
        return ACE == this;
    }

}
