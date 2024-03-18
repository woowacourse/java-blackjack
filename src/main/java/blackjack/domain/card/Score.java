package blackjack.domain.card;

import java.util.List;

public class Score {
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_ALTERNATIVE_SCORE = 10;

    private final int score;

    public Score(List<Card> cards) {
        this.score = calculateOptimizedScore(cards);
    }

    private int calculateOptimizedScore(List<Card> cards) {
        int cardTotalScore = getCardTotalScore(cards);
        if (cardTotalScore >= BLACKJACK_SCORE) {
            return cardTotalScore;
        }
        int aceCountForAlter = countAceForAlter(cardTotalScore, cards);
        return aceCountForAlter * ACE_ALTERNATIVE_SCORE + cardTotalScore;
    }

    private int getCardTotalScore(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAceForAlter(int cardTotalScore, List<Card> cards) {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        int availableAceAlterCount = (BLACKJACK_SCORE - cardTotalScore) / ACE_ALTERNATIVE_SCORE;
        return Math.min(aceCount, availableAceAlterCount);
    }

    public int getScore() {
        return this.score;
    }
}
