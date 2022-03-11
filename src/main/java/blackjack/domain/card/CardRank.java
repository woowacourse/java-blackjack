package blackjack.domain.card;

import static blackjack.domain.game.Score.ACE_HIGH_VALUE;

import blackjack.domain.game.Score;

public enum CardRank {

    ACE("A", Score.valueOf(1)),
    TWO("2", Score.valueOf(2)),
    THREE("3", Score.valueOf(3)),
    FOUR("4", Score.valueOf(4)),
    FIVE("5", Score.valueOf(5)),
    SIX("6", Score.valueOf(6)),
    SEVEN("7", Score.valueOf(7)),
    EIGHT("8", Score.valueOf(8)),
    NINE("9", Score.valueOf(9)),
    TEN("10", Score.valueOf(10)),
    JACK("J", Score.valueOf(10)),
    QUEEN("Q", Score.valueOf(10)),
    KING("K", Score.valueOf(10));

    private final String displayName;
    private final Score value;

    CardRank(final String displayName, final Score value) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Score getValue() {
        return value;
    }

    public Score getHighValue() {
        if (this == ACE) {
            return Score.valueOf(ACE_HIGH_VALUE);
        }
        return value;
    }
}
