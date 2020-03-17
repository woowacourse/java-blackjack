package blackjack.domain.card;

import blackjack.domain.player.Player;

public enum Type {
    ACE(11, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private static final int ACE_LOWER_POINT = 1;

    private final int point;
    private final String name;

    Type(int point, String name) {
        this.point = point;
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public int getPointUsingPreviousScore(int previousScore) {
        if (this == ACE && isOverBlackJackScoreIfAdd(previousScore)) {
            return ACE_LOWER_POINT;
        }
        return this.point;
    }

    private boolean isOverBlackJackScoreIfAdd(int previousScore) {
        return previousScore + this.point > Player.BLACKJACK_SCORE;
    }

    public String getName() {
        return name;
    }
}
