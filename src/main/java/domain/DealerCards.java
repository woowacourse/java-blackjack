package domain;

import java.util.List;

public class DealerCards extends Cards {

    private static final int MIN_SCORE = 16;

    public DealerCards(Participant participant, List<Card> cards) {
        super(participant, cards);
    }

    public boolean canDraw() {
        return sum() <= MIN_SCORE;
    }
}
