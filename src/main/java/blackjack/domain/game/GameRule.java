package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.List;

public class GameRule {
    private static final int MAX_SCORE = 21;
    private static final int ACE_INCREMENT = 10;

    public static boolean isBlackjack(List<Card> cards) {
        return cards.size() == 2
                && calculateTotalScore(cards) == MAX_SCORE;
    }

    public static boolean isBusted(List<Card> cards) {

        if (calculateTotalScore(cards) == 0) {
            return true;
        }
        return false;
    }

    private static int incrementAceScore(List<Card> cards, int score) {
        if (cards.stream().anyMatch(Card::isAce) && score <= MAX_SCORE - ACE_INCREMENT) {
            score += ACE_INCREMENT;
        }
        return score;
    }

    public static int calculateTotalScore(List<Card> cards) {
        int score = incrementAceScore(cards, cards.stream()
                .mapToInt(Card::getScore)
                .sum());
        if (score > MAX_SCORE) {
            return 0;
        }
        return score;
    }
}
