package domain.game;

public class Rule {
    private static final double PLAYER_FIRST_WIN_RATIO = 1.5;
    private static final double PLAYER_WIN_RATIO = 2.0;
    private static final double PLAYER_LOSE_RATIO = -1.0;
    private static final int DEALER_RESULT_RATIO = -1;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACKJACK_FULL_SCORE = 21;
    public static final int DRAW_MAX_SCORE = 16;
    protected static final int FIRST_DRAW_NUMBER = 2;

    public static double getPlayerWinRatio() {
        return PLAYER_WIN_RATIO;
    }

    public static double getPlayerLoseRatio() {
        return PLAYER_LOSE_RATIO;
    }

    public static int getDealerResultRatio() {
        return DEALER_RESULT_RATIO;
    }

    public static double getPlayerFirstWinRatio() {
        return PLAYER_FIRST_WIN_RATIO;
    }

    public static int getAceBonusScore() {
        return ACE_BONUS_SCORE;
    }

    public static int getBlackjackFullScore() {
        return BLACKJACK_FULL_SCORE;
    }

    public static double getDrawMaxScore() {
        return DRAW_MAX_SCORE;
    }

    public static int getFirstDrawNumber(){
        return FIRST_DRAW_NUMBER;
    }
}
