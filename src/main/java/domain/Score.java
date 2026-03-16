package domain;

public class Score {
    private final int value;
    private final boolean isBlackJack;

    public Score(int value, boolean isBlackJack) {
        this.value = value;
        this.isBlackJack = isBlackJack;
    }

    public WinningStatus compareTo(Score other) {
        if (value > other.value) {
            return WinningStatus.WIN;
        }
        if (value < other.value) {
            return WinningStatus.LOSE;
        }
        return WinningStatus.DRAW;
    }

    public boolean isBurst() {
        return value > BlackjackRule.BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return isBlackJack;
    }
}
