package domain;

import java.util.List;

public class DealerCards extends Cards implements Drawable {

    private static final int MIN_SCORE = 16;

    public DealerCards(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canDraw() {
        return sum() <= MIN_SCORE;
    }
}
