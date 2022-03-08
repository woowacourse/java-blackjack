package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private static final int ADDITIONAL_SCORE_FOR_ACE = 10;

    private int score = 0;
    private final List<Card> cards = new ArrayList<>();

    Participant(List<Card> cards) {
        this.cards.addAll(cards);
        for (Card card : cards) {
            addScore(card);
        }
    }

    private void addScore(Card card) {
        score += card.getValue();
    }

    public void addCard(Card card) {
        cards.add(card);
        addScore(card);
    }

    public void endTurn() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        boolean changed = true;
        while (aceCount-- > 0 && changed) {
            changed = moreScoreForAceOrNot();
        }
    }

    private boolean moreScoreForAceOrNot() {
        if (score + ADDITIONAL_SCORE_FOR_ACE <= 21) {
            score += ADDITIONAL_SCORE_FOR_ACE;
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }
}
