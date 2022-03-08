package blackjack.domain;

import java.util.List;

public class Player extends Participant {

    public Player(List<Card> cards) {
        super(cards);
    }

    public boolean canHit() {
        return getScore() < GOAL_SCORE;
    }
}
