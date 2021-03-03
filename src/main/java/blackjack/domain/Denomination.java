package blackjack.domain;

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
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String denomination;
    private int score;

    private Denomination(String denomination, int score) {
        this.denomination = denomination;
        this.score = score;
    }

    public static Denomination of(String denomination){
        return Arrays.stream(Denomination.values())
                .filter(den -> den.denomination.equals(denomination))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 Denomination입니다."));
    }

    public String getDenomination() {
        return denomination;
    }

    public int getScore() {
        return score;
    }
}
