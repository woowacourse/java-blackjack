package blackjack.domain.card;

import java.util.Arrays;

public enum Denomination {
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
    JACK("J", new Score(10)),
    QUEEN("Q", new Score(10)),
    KING("K", new Score(10));

    private final String denomination;
    private final Score score;

    Denomination(final String denomination, Score score) {
        this.denomination = denomination;
        this.score = score;
    }

    public static Denomination of(final String denomination) {
        return Arrays.stream(Denomination.values())
                .filter(value -> value.denomination.equals(denomination))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Denomination입니다."));
    }

    public String denomination() {
        return denomination;
    }

    public Score getScore() {
        return score;
    }
}
