package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.List;

public class Score {
    private static final int BUST_NUMBER = 22;
    private static final int ACE_HIDDEN_SCORE = 10;

    private int score;

    public void calculateScore(List<Card> cards, boolean isContainAce) {
        score = cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
        if (isContainAce && score + ACE_HIDDEN_SCORE < BUST_NUMBER) {
            score += ACE_HIDDEN_SCORE;
        }
    }

    public int getScore() {
        return score;
    }
}