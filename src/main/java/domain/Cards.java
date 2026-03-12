package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BUST_SCORE = 21;
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateOptimalScore() {
        int sum = calculateScore();
        for (int i = countAce(); i > 0 && isBust(sum); i--) {
            sum -= 10;
        }
        return sum;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean canReceiveCard(int bustThreshold) {
        int sum = calculateOptimalScore();
        return !isBust(sum) && sum < bustThreshold;
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .toList();
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

    private boolean isBust(int score) {
        return score > BUST_SCORE;
    }
}
