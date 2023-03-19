package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class TotalScore {
    private static final int ACE_VALUE_GAP = 10;
    private static final int LIMIT_SCORE = 21;

    private final int totalScore;

    private TotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public static TotalScore calculateTotalScore(List<Card> cards) {
        int newTotalScore = 0;
        for (Card card : cards) {
            newTotalScore += card.getCardNumber().getValue();
        }
        newTotalScore = updateAceScore(newTotalScore, cards);
        return new TotalScore(newTotalScore);
    }

    private static int updateAceScore(int score, List<Card> cards) {
        int totalScore = score;
        int aceSize = countAce(cards);

        while (aceSize > 0 && totalScore > LIMIT_SCORE) {
            totalScore -= ACE_VALUE_GAP;
            aceSize--;
        }
        return totalScore;
    }

    private static int countAce(List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int getTotalScore() {
        return totalScore;
    }
}
