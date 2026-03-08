package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> showHand() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public int calculateScore() {
        int totalScore = cards.stream().mapToInt(Card::getScore).sum();
        return totalScore;
    }
}
