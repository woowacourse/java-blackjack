package domain.player;

import domain.card.Card;
import java.util.List;

public class Dealer extends Player {
    private static final int DEALER_STOP_SCORE = 17;

    public boolean canStand() {
        int score = score();
        if (isBust()) {
            return true;
        }
        return score >= DEALER_STOP_SCORE;
    }

    public Card getFirstCard() {
        return handCard.getFirstCardInfo();
    }

    public List<Card> getCards() {
        return handCard.getCards();
    }
}
