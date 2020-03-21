package domain.user;

import domain.game.Rule;

public class Dealer extends User {
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
        return handCard.getScore() <= Rule.getDrawMaxScore();
    }
}
