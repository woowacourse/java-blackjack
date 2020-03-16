package domain.user;

import static domain.rule.Drawable.BLACKJACK_FULL_SCORE;

public class Dealer extends User {
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
}
