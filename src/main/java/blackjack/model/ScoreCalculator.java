package blackjack.model;

import java.util.List;

public class ScoreCalculator {

    public int calculateScore(List<Card> cards) {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        long aceCount = cards.stream()
                .filter(card -> card.rank() == Rank.ACE)
                .count();

        for (int i = 0; i < aceCount; i++) {
            if (score + 10 < 21) {
                break;
            }
            score += 10;
        }

        return score;
    }
}
