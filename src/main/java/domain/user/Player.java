package domain.user;

import static domain.user.HandCard.BLACKJACK_FULL_SCORE;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isDrawable() {
        return getScore() <= BLACKJACK_FULL_SCORE;
    }
}
