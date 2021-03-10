package blackjack.domain.card;

import java.util.Arrays;

public enum Denomination {
    ACE("A", Score.of(1)),
    TWO("2", Score.of(2)),
    THREE("3", Score.of(3)),
    FOUR("4", Score.of(4)),
    FIVE("5", Score.of(5)),
    SIX("6", Score.of(6)),
    SEVEN("7", Score.of(7)),
    EIGHT("8", Score.of(8)),
    NINE("9", Score.of(9)),
    TEN("10", Score.of(10)),
    JACK("J", Score.of(10)),
    QUEEN("Q", Score.of(10)),
    KING("K", Score.of(10));

    private final String denomination;
    private final Score score;

    Denomination(final String denomination, final Score score) {
        this.denomination = denomination;
        this.score = score;
    }

    public static Denomination of(final String denomination) {
        return Arrays.stream(Denomination.values())
                .filter(den -> den.denomination.equals(denomination))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Denomination입니다."));
    }

    public Score getScore() {
        return score;
    }

    public String getDenomination() {
        return denomination;
    }
}
