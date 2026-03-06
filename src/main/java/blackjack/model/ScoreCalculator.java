package blackjack.model;

import java.util.List;

public class ScoreCalculator {

    private final AceAdjustPolicy aceAdjustPolicy;

    public ScoreCalculator(AceAdjustPolicy aceAdjustPolicy) {
        this.aceAdjustPolicy = aceAdjustPolicy;
    }

    public int calculateScore(List<Card> cards) {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        return aceAdjustPolicy.adjust(score, cards);
    }
}
