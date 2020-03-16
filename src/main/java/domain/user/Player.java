package domain.user;

import static domain.user.HandCard.BLACKJACK_FULL_SCORE;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public boolean isDrawable() {
        return handCard.getScore() <= BLACKJACK_FULL_SCORE;
    }
}
