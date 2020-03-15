package domain.user;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    public static final int DRAW_MAX_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public String getFirstStatus() {
        return getStatus().split(",")[0];
    }

    @Override
    public boolean isDrawable() {
        return getScore() <= DRAW_MAX_SCORE;
    }

    public boolean isWinner(User user) {
        return isBustWin(user) || isBlackJackWin(user) || isScoreWin(user);
    }

    private boolean isBustWin(User user) {
        return !isBust() && user.isBust();
    }

    private boolean isBlackJackWin(User user) {
        return isBlackJack() && !user.isBlackJack();
    }

    private boolean isScoreWin(User user) {
        return user.getScore() <= getScore() && !isBust();
    }
}
