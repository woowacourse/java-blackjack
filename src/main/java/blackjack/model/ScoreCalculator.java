package blackjack.model;

import java.util.List;

public class ScoreCalculator {

    public static Score calculate(List<Card> cards) {
        long aceCount = cards.stream()
                .filter(card -> Number.ACE.equals(card.getNumber()))
                .count();

        int total = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        return new Score(aceTranslate(total, aceCount));
    }

    private static int aceTranslate(int score, long aceCount) {
        while (score <= 11 && aceCount > 0) {
            score += 10;
            aceCount--;
        }
        return score;
    }
}
