package domain.card;

import java.util.List;

public class PlayerCards extends Cards implements Drawable {

    public PlayerCards(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canDraw() {
        return bestSum() <= MAX_SCORE;
    }
}
