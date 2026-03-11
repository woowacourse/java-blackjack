package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateOptimalScore() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        int sum = calculateScore();
        while (aceCount != 0 && isBust(sum)) {
            sum -= 10;
            aceCount--;
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

    private int calculateScore() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }
        return sum;
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .toList();
    }

    private boolean isBust(int score) {
        return score > 21;
    }
}
