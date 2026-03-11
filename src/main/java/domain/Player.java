package domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends Participant{

    public Player(String name) {
        super(name);
    }


    public int calculateScore() {
        int score = 0;
        boolean hasAce = false;

        for (Card card : getCards()) {
            score += card.getScore();
            if (card.isAce()) {
                hasAce = true;
            }
        }
        return applyAceBonus(score, hasAce);
    }

    private int applyAceBonus(int score, boolean hasAce) {
        if (isSoftHand(score, hasAce)) {
            return score + 10;
        }
        return score;
    }

    private boolean isSoftHand(int score, boolean hasAce) {
        return hasAce && score + 10 <= 21;
    }


}
