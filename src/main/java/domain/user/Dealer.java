package domain.user;

import static domain.user.HandCard.BLACKJACK_FULL_SCORE;

public class Dealer extends User {
    public static final int DRAW_MAX_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public String getFirstStatus() {
        return getStatus().split(",")[0];
    }

    public boolean isWinner(User user) {
        if (getScore() > BLACKJACK_FULL_SCORE) {
            return false;
        }
        return user.getScore() > BLACKJACK_FULL_SCORE || handCard.getScore() >= user.getScore();
    }

    public boolean isDrawable() {
        return handCard.getScore() <= DRAW_MAX_SCORE;
    }
}
