package domain.user;

import domain.card.Card;
import java.util.List;

public class Dealer extends User {

    private static final int DRAWABLE_LIMIT_SCORE = 17;

    @Override
    public boolean isDrawable() {
        if (!state.isDrawable()) {
            return false;
        }
        int score = state.getScore();
        return score < DRAWABLE_LIMIT_SCORE;
    }

    public Card getFirstCard() {
        List<Card> cards = state.getCards();
        return cards.get(0);
    }
}
