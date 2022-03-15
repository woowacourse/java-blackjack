package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int MAX_SCORE = 21;
    private static final int SCORE_ABLE_TO_OPTIMIZE = 11;
    private static final int ADJUST_NUM = 10;

    private List<Card> cards;

    private Cards() {
        cards = new ArrayList<>();
    }

    public static Cards generateCards() {
        return new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int score() {
        int score = cards.stream()
                .map(Card::type)
                .mapToInt(BlackjackCardType::score)
                .sum();
        return optimizeScore(score);
    }

    public boolean isBurst(int score) {
        return score > MAX_SCORE;
    }

    public List<Card> getCards() {
        return cards;
    }

    private int optimizeCount() {
        return (int) cards.stream()
                .map(Card::type)
                .map(BlackjackCardType::score)
                .filter(score -> score.equals(SCORE_ABLE_TO_OPTIMIZE))
                .count();
    }

    private int optimizeScore(int scoreSum) {
        int optimizeCount = optimizeCount();
        while (isBurst(scoreSum) && optimizeCount > 0) {
            scoreSum -= ADJUST_NUM;
        }
        return scoreSum;
    }
}
