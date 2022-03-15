package blackJack.domain.card;

import java.util.Set;

public class Score {

    private static final int BLACK_JACK = 21;
    private static final int ACE_BONUS_SCORE = 10;

    public int calculateFinalScore(Set<Card> cards) {
        final int score = calculateCardsSum(cards);
        if (hasAce(cards) && checkValidationAceBonusScore(score)) {
            return score + ACE_BONUS_SCORE;
        }
        return score;
    }

    private boolean hasAce(Set<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean checkValidationAceBonusScore(int score) {
        return score + ACE_BONUS_SCORE <= BLACK_JACK;
    }

    private int calculateCardsSum(Set<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}
