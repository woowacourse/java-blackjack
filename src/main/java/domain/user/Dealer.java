package domain.user;

public class Dealer extends User {
    public static final int DRAW_MAX_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isWinner(User user) {
        if (handCard.isOver()) {
            return false;
        }
        return user.isOver() || handCard.getScore() >= user.getScore();
    }

    @Override
    public boolean isDrawable() {
        return handCard.getScore() <= DRAW_MAX_SCORE;
    }
}
