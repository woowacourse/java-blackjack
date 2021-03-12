package blackjack.domain.card;

import java.util.Arrays;

public enum Denomination {
    ACE("A", Score.from(1)),
    TWO("2", Score.from(2)),
    THREE("3", Score.from(3)),
    FOUR("4", Score.from(4)),
    FIVE("5", Score.from(5)),
    SIX("6", Score.from(6)),
    SEVEN("7", Score.from(7)),
    EIGHT("8", Score.from(8)),
    NINE("9", Score.from(9)),
    TEN("10", Score.from(10)),
    JACK("J", Score.from(10)),
    QUEEN("Q", Score.from(10)),
    KING("K", Score.from(10));

    private final String denomination;
    private final Score score;

    Denomination(final String denomination, final Score score) {
        this.denomination = denomination;
        this.score = score;
    }

    public static Denomination from(final String denomination) {
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

    public boolean isAce() {
        return this.equals(Denomination.ACE);
    }
}
