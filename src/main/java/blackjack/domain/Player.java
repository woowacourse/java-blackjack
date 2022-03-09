package blackjack.domain;

import java.util.List;

public class Player extends Participant {

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    public boolean canHit() {
        return getScore() < GOAL_SCORE;
    }

    public boolean isWin(int score) {
        if (getScore() > GOAL_SCORE) {
            return false;
        }
        if (score > GOAL_SCORE) {
            return true;
        }
        return getScore() >= score;
    }
}
