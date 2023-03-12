package domain.user;

import domain.card.Card;
import java.util.List;

public class Dealer extends User {

    private static final int HITTABLE_LIMIT_SCORE = 17;

    @Override
    public boolean isHittable() {
        if (!state.isHittable()) {
            return false;
        }
        int score = state.getScore();
        return score < HITTABLE_LIMIT_SCORE;
    }

    public Card getFirstCard() {
        List<Card> cards = state.getCards();
        return cards.get(0);
    }
}
