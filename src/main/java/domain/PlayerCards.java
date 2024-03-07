package domain;

import java.util.List;

public class PlayerCards extends Cards {

    private static final int MAX_SCORE = 21;

    public PlayerCards(Participant participant, List<Card> cards) {
        super(participant, cards);
    }

    public boolean canDraw() {
        return sum() <= MAX_SCORE;
    }
}
