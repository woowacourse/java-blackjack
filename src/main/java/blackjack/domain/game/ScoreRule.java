package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.List;

public class ScoreRule {
    private static final int MAX_SCORE = 21;
    private static final int ACE_INCREMENT = 10;

    public static boolean isBlackjack(List<Card> cards) {
        return cards.size() == 2
                && calculateTotalScore(cards) == MAX_SCORE;
    }

    public static boolean isBusted(List<Card> cards) {
        return calculateTotalScore(cards) == 0;
    }

    private static int incrementAceScore(List<Card> cards, int score) {
        if (containsAce(cards) && score <= MAX_SCORE - ACE_INCREMENT) {
            score += ACE_INCREMENT;
        }
        return score;
    }

    private static boolean containsAce(List<Card> cards) {
        return cards.stream().anyMatch(Card::isAce);
    }


    public static int calculateTotalScore(List<Card> cards) {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        score = incrementAceScore(cards, score);
        if (score > MAX_SCORE) {
            return 0;
        }
        return score;
    }
}
