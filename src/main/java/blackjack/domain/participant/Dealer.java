package blackjack.domain.participant;

import blackjack.domain.Card;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int DRAWABLE_MAX_SCORE = 16;

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    protected int getMaxDrawableScore() {
        return DRAWABLE_MAX_SCORE;
    }
}
