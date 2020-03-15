package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.List;

public class Result {
    private static final int BUST_NUMBER = 22;
    private static final int ACE_HIDDEN_SCORE = 10;
    private static final int DRAW_CARD_PIVOT = 21;

    private MatchResult matchResult;
    private int score;

    public void calculateWithAce(List<Card> cards, boolean isContainAce) {
        calculateScore(cards);
        if (isContainAce && score + ACE_HIDDEN_SCORE < BUST_NUMBER) {
            score += ACE_HIDDEN_SCORE;
        }
    }

    private void calculateScore(List<Card> cards) {
        score = cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }

    public MatchResult isMatchResult(int dealerScore) {
        if (score > DRAW_CARD_PIVOT) {
            return matchResult = MatchResult.LOSE;
        }

        if (dealerScore > DRAW_CARD_PIVOT) {
            return matchResult = MatchResult.WIN;
        }

        return matchResult = MatchResult.of(score - dealerScore);
    }

    public int getScore() {
        return score;
    }
}