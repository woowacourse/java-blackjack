package domain.player.attribute;

import domain.card.Card;
import static util.Constants.ACE_SCORE;
import static util.Constants.DEALER_REFERENCE_POINT;

public class Score {

    private int score = 0;

    public void addValue(Card card){
        if (card.isAce()) {
            calculateAceCard(card);
            return;
        }
        score += card.getValue();
    }

    private void calculateAceCard(Card card) {
        if (score + ACE_SCORE > DEALER_REFERENCE_POINT) {
            score += card.getValue();
            return;
        }
        score += ACE_SCORE;
    }

    public int getScore() {
        return score;
    }
}
