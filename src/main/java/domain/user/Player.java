package domain.user;

import static domain.card.HandCard.PERFECT_SCORE;

public class Player extends BlackjackUser {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isDrawable() {
        return getScore() <= PERFECT_SCORE;
    }
}
