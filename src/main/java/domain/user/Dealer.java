package domain.user;

public class Dealer extends BlackjackUser {
    private static final String DEALER_NAME = "딜러";
    public static final int DRAW_MAX_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isDrawable() {
        return getScore() <= DRAW_MAX_SCORE;
    }
}
