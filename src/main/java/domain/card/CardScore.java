package domain.card;

import java.util.Arrays;

public enum CardScore {
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

    private final String score;
    private final int number;

    CardScore(String score, int number) {
        this.score = score;
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public String getScore() {
        return this.score;
    }

    public static CardScore of(final String score){
        return Arrays.stream(values())
                .filter(val -> val.score.equals(score))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("생성할 수 없는 value 입니다."));
    }
}
