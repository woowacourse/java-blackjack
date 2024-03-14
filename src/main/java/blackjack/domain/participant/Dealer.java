package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int DRAWABLE_MAX_SCORE = 16;
    private static final int START_CARD_SIZE = 1;

    public Dealer() {
        super(Collections.emptyList());
    }

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    protected int getMaxDrawableScore() {
        return DRAWABLE_MAX_SCORE;
    }

    @Override
    protected int getStartCardSize() {
        return START_CARD_SIZE;
    }
}
