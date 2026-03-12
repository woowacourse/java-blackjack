package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalScore() {
        int baseScore = calculateBaseScore();
        int aceCount = calculateAceCount();

        return calculateTotalScoreWithAceCalculation(baseScore, aceCount);
    }

    private int calculateAceCount() {
        return cards.stream()
                .filter(Card::isAce)
                .toList()
                .size();
    }

    private Integer calculateBaseScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);
    }

    private int calculateTotalScoreWithAceCalculation(int score, int aceCount) {
        while (score > BLACKJACK_SCORE && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    public boolean isBust() {
        return calculateTotalScore() > BLACKJACK_SCORE;
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCard() {
        return cards.stream()
                .toList();
    }
}
