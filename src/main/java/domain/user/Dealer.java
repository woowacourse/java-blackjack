package domain.user;

import static domain.user.HandCard.BLACKJACK_FULL_SCORE;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_MAX_SCORE = 16;

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
        return BLACKJACK_FULL_SCORE < user.getScore() || user.getScore() <= getScore();
    }
}
