package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateOptimalScore() {
        int sum = calculateScore();
        for (int i = countAce(); i > 0; i--) {
            if (isLessThanBustScore(sum)) {
                break;
            }
            sum -= 10;
        }
        return sum;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean canReceiveCard(int hitThreshold) {
        int sum = calculateOptimalScore();
        return isLessThanBustScore(sum) && sum <= hitThreshold;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean isLessThanBustScore(int score) {
        return score <= BLACKJACK_SCORE;
    }
}
