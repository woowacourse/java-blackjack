package domain.player;

import domain.card.Card;

public class Score {

    private int score = 0;

    private static final int ACE_SCORE = 11;

    public void addValue(Card card){
        if (card.isAce()) {
            calculateAceCard(card);
            return;
        }
        score += card.getValue();
    }

    public int getScore() {
        return score;
    }

    private void calculateAceCard(Card card) {
        if (score + ACE_SCORE > 21) {
            score += card.getValue();
            return;
        }
        score += ACE_SCORE;
    }

}
